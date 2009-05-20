package mobilis.impl.adaptation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import mobilis.api.ReceptacleInfo;
import mobilis.api.ServiceInfo;
import mobilis.api.adaptation.IAdaptationManager;
import mobilis.api.control.IComponentManager;
import mobilis.api.control.IComponentManagerListener;
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

public class AdaptationManager implements IAdaptationManager, IComponentManagerListener {

	private ContextWrapper contextWrapper;
	private IProviderService contextProvider;
	private IComponentManager.Stub componentManager;
	
	private long callId = 10000;
	private HashMap<Long, String> requests;
	
	public AdaptationManager(ContextWrapper contextWrapper, IComponentManager.Stub componentManager) {
		this.componentManager = componentManager;
		this.contextWrapper = contextWrapper;
		
		Intent intent = new Intent(mobilis.context.IProviderService.class.getName());
		this.contextWrapper.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
		
		requests = new HashMap<Long, String>();
		try {
			this.componentManager.addListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		ComponentState componentState = getComponentState(oldComponentName);
		
		for (ReceptacleInfo rec : componentState.getReceptacles()) {
			Log.d(this.getClass().getName(), "receptacle: " + rec.getName() + ", provider: " + rec.getComponentName());
		}
		for (Entry<ServiceInfo, List<ReceptacleInfo>> entry : componentState.getServices().entrySet()) {
			
			Log.d(this.getClass().getName(), "service: " + entry.getKey().getName());
			for (ReceptacleInfo rec : componentState.getReceptacles()) {
				Log.d(this.getClass().getName(), "connected to: " + rec.getName() + ", consumer: " + rec.getComponentName());
			}
		}
		
		
		// unload current component
		unloadComponent(oldComponentName);
		
		// load new component
		loadComponent(newComponentName);
		
		// set new component state
		setComponentState(newComponentName, componentState);		
	}
	
	private ComponentState getComponentState(String componentName) {
		ComponentState componentState = new ComponentState();
		try {
			ILocalLoader loader = componentManager.getByName(componentName);

			// Find out to which services the receptacles are bound
			List<String> receptacleNames = new ArrayList<String>();
			loader.getReceptacleNames(receptacleNames);
			for (String receptacleName : receptacleNames) {
				ReceptacleInfo receptacleInfo = loader.getReceptacleInfo(receptacleName);
				ServiceInfo connectedServiceInfo = receptacleInfo.getServiceInfo();
				
				Log.d(this.getClass().getName(), "receptacle " + receptacleName + " is bound: " + receptacleInfo.isBound());
				
				if (connectedServiceInfo != null) {
					Log.d(this.getClass().getName(), "receptacle " + receptacleName + " is connected to component " + connectedServiceInfo.getComponentName());
					if (connectedServiceInfo.equals(componentName)) {
						// Service is provided by this component
						componentState.addReceptacle(receptacleInfo);
					}
				}
			}
			
			// Find out to each receptacles the services are bound
			List<String> componentNames = componentManager.getAllNames();
			for (String name : componentNames) {
				loader = componentManager.getByName(name);
				
				receptacleNames = new ArrayList<String>();
				loader.getReceptacleNames(receptacleNames);
				for (String receptacleName : receptacleNames) {
					ReceptacleInfo receptacleInfo = loader.getReceptacleInfo(receptacleName);
					ServiceInfo connectedServiceInfo = receptacleInfo.getServiceInfo();
					if (connectedServiceInfo != null) {
						if (connectedServiceInfo.getComponentName().equals(componentName)) {
							Log.d(this.getClass().getName(), "receptacle " + receptacleName + " is connected to component " + connectedServiceInfo.getComponentName());
							
							componentState.addService(connectedServiceInfo, receptacleInfo);
						}
					}
				}
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return componentState;
	}
	
	/**
	 * Connects component's services and receptacles, based on a ComponentState object
	 * @param componentName
	 * @param componentState
	 */
	private void setComponentState(String componentName, ComponentState componentState) {
		// After starting the component, it's necessary to re-build its state, if there is one
		
	}
	
	private void unloadComponent(String componentName) {
		try {
			ILocalLoader loader = componentManager.getByName(componentName);
			
			// Notify the component
			loader.stop();

			// Disconnect all the components services and the component loader service
			componentManager.unloadComponent(componentName);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadComponent(String componentName) {
		try {
			Log.i(this.getClass().getName(), "loading: " + componentName);
			requests.put(callId, componentName);
			
			List<String> componentNames = new ArrayList<String>();
			componentNames.add(componentName);
			
			componentManager.loadComponents(componentNames, callId);
			Log.d(this.getClass().getName(), "load component: " + callId + ", " + componentName);
			callId++;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	private AdaptationManager getContextWrapper() {
		return this;
	}

	@Override
	public void componentsLoaded(long callId) throws RemoteException {
		if (requests.containsKey(callId)) {
			String componentName = requests.get(callId);
			Log.d(this.getClass().getName(), "componentLoaded: " + callId + ", " + componentName);
			ILocalLoader loader = componentManager.getByName(componentName);
			loader.start();
			requests.remove(callId);
		}
	}

}
