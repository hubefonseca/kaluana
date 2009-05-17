package mobilis.impl.adaptation;

import java.util.ArrayList;
import java.util.List;

import mobilis.api.IService;
import mobilis.api.adaptation.IAdaptationManager;
import mobilis.api.control.IComponentManager;
import mobilis.api.control.ILocalLoader;
import mobilis.context.IProviderService;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AdaptationManager implements IAdaptationManager {

	private ContextWrapper contextWrapper;
	private IProviderService contextProvider;
	private IComponentManager.Stub componentManager;
	
	public AdaptationManager(ContextWrapper contextWrapper, IComponentManager.Stub componentManager) {
		this.componentManager = componentManager;
		this.contextWrapper = contextWrapper;
		
		Intent intent = new Intent(mobilis.context.IProviderService.class.getName());
		this.contextWrapper.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
	}
	
	public ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			contextProvider = IProviderService.Stub.asInterface(service);
			
			Log.d(this.getClass().getName(), "Location provider service connected!");
			
			try {
				contextProvider.registerAdaptationManager(getContextWrapper());
				contextProvider.start();
			} catch(RemoteException e) {
				
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	/**
	 * There are two cases for adaptation:
	 * 1 - User enters a more specific domain
	 * 2 - User leaves a specific domain and browses for a more general component
	 */
	@Override
	public void onContextChange(mobilis.context.Context context) throws RemoteException {
		Log.d(this.getClass().getName(), "new place: " + context.getLocation());
		
		String domain = context.getLocation();
		
		List<String> candidatesToGetMoreSpecific = new ArrayList<String>();
		List<String> candidatesToGetLessSpecific = new ArrayList<String>();
		String candidate = null;
		
		// If a loaded component can not operate on this context, 
		// search for less specific components
		List<String> componentNames = componentManager.getAllNames();
		for (String name : componentNames) {
			candidatesToGetLessSpecific.add(name);
		}
		
		// Search for more specific components
		Intent intent = new Intent(domain);
		for (ResolveInfo availableCompInfo : contextWrapper.getPackageManager().queryIntentServices(intent, 0)) {
			if (!componentManager.isLoaded(availableCompInfo.serviceInfo.name)) {
				// The available component is not loaded. Verify if it can replace another one
				String category = availableCompInfo.serviceInfo.name.substring(0, availableCompInfo.serviceInfo.name.lastIndexOf("."));
				intent = new Intent(category);
				for (ResolveInfo categoryCompInfo : contextWrapper.getPackageManager().queryIntentServices(intent, 0)) {
					if (componentManager.isLoaded(availableCompInfo.serviceInfo.name)) {
						// There are components registered to the same category - candidates to be replaced
						candidatesToGetMoreSpecific.add(categoryCompInfo.serviceInfo.name);
					}
				}
				
				// Browse for loaded components with less specific domain
				domain = context.getLocation();
				while (domain != null) {
					intent = new Intent(domain);
					for (ResolveInfo domainCompInfo : contextWrapper.getPackageManager().queryIntentServices(intent, 0)) {
						if (componentManager.isLoaded(domainCompInfo.serviceInfo.name)) {
							if (candidatesToGetMoreSpecific.contains(domainCompInfo.serviceInfo.name)) {
								candidate = domainCompInfo.serviceInfo.name;
							}
						}
					}
					if (domain.indexOf(".") >= 0) {
						domain = domain.substring(0, domain.lastIndexOf("."));
					} else {
						domain = null;
					}
				}
			} else {
				Log.d(this.getClass().getName(), availableCompInfo.serviceInfo.name.substring(0, availableCompInfo.serviceInfo.name.indexOf("Loader")) + " is loaded and operating well");
				candidatesToGetLessSpecific.remove(availableCompInfo.serviceInfo.name.substring(0, availableCompInfo.serviceInfo.name.indexOf("Loader")));
			}
			
			if (candidate != null) {
				Log.d(this.getClass().getName(), candidate + " must be replaced by " + availableCompInfo.serviceInfo.name + "(more specific)");
				replaceComponents(candidate, availableCompInfo.serviceInfo.name);
			}
		}
		
		// Search for less specific components to replace the specific ones		
		
		// Verify if the loaded components that don't belong to this domain belong to a more generic one
		// If not, browse for other component on the same category
		// Discover the candidate to replace the component
			// For every component, find its category
			// Browse for components on less specific domains on the same category
		domain = context.getLocation();
		while (domain != null) {
			intent = new Intent(domain);
			for (ResolveInfo domainCompInfo : contextWrapper.getPackageManager().queryIntentServices(intent, 0)) {
				if (componentManager.isLoaded(domainCompInfo.serviceInfo.name)) {
					candidatesToGetLessSpecific.remove(domainCompInfo.serviceInfo.name);
				}
			}
			if (domain.indexOf(".") >= 0) {
				domain = domain.substring(0, domain.lastIndexOf("."));
			} else {
				domain = null;
			}
			// não tá tirando o locationProviderComponent da lista de candidatos quando entra na puc.... :(
		}
		
		for (String name : candidatesToGetLessSpecific) {
			String category = componentManager.getByName(name).getCategory();
			
			domain = context.getLocation();
			candidate = null;
			while (domain != null) {
				intent = new Intent(domain);
				for (ResolveInfo domainCompInfo : contextWrapper.getPackageManager().queryIntentServices(intent, 0)) {
					Intent categoryIntent = new Intent(category);
					for (ResolveInfo catCompInfo : contextWrapper.getPackageManager().queryIntentServices(categoryIntent, 0)) {
						if (!componentManager.isLoaded(catCompInfo.serviceInfo.name)) {	
							if (domainCompInfo.serviceInfo.name.equals(catCompInfo.serviceInfo.name)) {
								// The candidate has same category and less specific domain
								candidate = catCompInfo.serviceInfo.name.substring(0, catCompInfo.serviceInfo.name.lastIndexOf("Loader"));
							}
						}
					}
				}
				if (domain.indexOf(".") >= 0) {
					domain = domain.substring(0, domain.lastIndexOf("."));
				} else {
					domain = null;
				}
			}
			if (candidate != null) {
				Log.d(this.getClass().getName(), name + " must be replaced by " + candidate + "(less specific)");
				replaceComponents(name, candidate);
			} else {
				Log.d(this.getClass().getName(), "There is no candidate to replace " + name);
			}
		}
	}

	private void replaceComponents(String oldComponentName, String newComponentName) {
		ConnectionState connectionState = unloadComponent(oldComponentName);
		
//		for (ReceptacleState rec : connectionState.receptacles.values()) {
//			Log.d(this.getClass().getName(), "provider: " + rec.getComponentName());
//		}
		
		// load new component
	}
	
	private ConnectionState unloadComponent(String componentName) {
		ConnectionState connectionState = new ConnectionState();
		try {
			ILocalLoader loader = componentManager.getByName(componentName);
			
			// Find out to which services the receptacles are bound
			List<String> receptacleNames = new ArrayList<String>();
			loader.getReceptacleNames(receptacleNames);
			for (String receptacleName : receptacleNames) {
				IService service = loader.getBoundService(receptacleName);
				connectionState.addReceptacle(receptacleName, service);
				// Tem que buscar o nome do componente a partir do loader 
				// (passar o IService entre diferentes processos é impossível)
				Log.d(this.getClass().getName(), "receptacle " + receptacleName + " is connected to component ...");
			}
			
			// Find out to each receptacles the services are bound
			List<String> componentNames = componentManager.getAllNames();
			for (String name : componentNames) {
				loader = componentManager.getByName(name);
				
				receptacleNames = new ArrayList<String>();
				loader.getReceptacleNames(receptacleNames);
				for (String receptacleName : receptacleNames) {
//					if (providerComponentName.equals(componentName)) {
//						connectionState.addService(name, providerComponentName, receptacleName);
//						Log.d(this.getClass().getName(), "persist this service state");
//					}
				}
			}
			
			// Notify the component
			loader.stop();
			
			// Unload the component
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connectionState;
	}
	
	private void loadComponent(String componentName) {
		// After starting the component, it's necessary to re-build its state, if there is a state
		
	}
	
	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	private AdaptationManager getContextWrapper() {
		return this;
	}

}
