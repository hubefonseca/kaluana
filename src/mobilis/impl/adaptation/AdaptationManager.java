package mobilis.impl.adaptation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import mobilis.api.ComponentInfo;
import mobilis.api.ReceptacleInfo;
import mobilis.api.ServiceInfo;
import mobilis.api.adaptation.IAdaptationManager;
import mobilis.api.control.IComponentManager;
import mobilis.api.control.IComponentManagerListener;
import mobilis.api.control.ILocalLoader;
import mobilis.context.IProviderService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AdaptationManager implements IAdaptationManager,
		IComponentManagerListener {

	private Context applicationContext;
	private IProviderService contextProvider;
	private IComponentManager.Stub componentManager;

	private long callId = 10000;
	private HashMap<Long, String> requests;
	private HashMap<String, ComponentInfo> componentsInfo = new HashMap<String, ComponentInfo>();

	/**
	 * Creates an adaptation manager
	 */
	public AdaptationManager(Context androidContextWrapper,
			IComponentManager.Stub componentManager) {
		this.componentManager = componentManager;
		this.applicationContext = androidContextWrapper;

		Intent intent = new Intent(mobilis.context.IProviderService.class
				.getName());
		this.applicationContext.bindService(intent, mServiceConnection,
				Context.BIND_AUTO_CREATE);

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

			Log.d(this.getClass().getName(),
					"Location provider service connected!");

			try {
				contextProvider.registerAdaptationManager(getContextWrapper());
				contextProvider.start();
			} catch (RemoteException e) {

			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d(this.getClass().getName(), "Adaptation manager disconnected");
		}

	};

	/**
	 * There are two cases that start an adaptation: 1 - The component is
	 * inadequate 2 - The component is invalid
	 */
	@Override
	public void onContextChange(mobilis.context.Context context)
			throws RemoteException {
		Log.d(this.getClass().getName(), "new place: " + context.getLocation());

		String domain = context.getLocation();

		// All loaded components
		for (String componentName : componentManager.getLoadedComponentNames()) {
			ILocalLoader componentLoader = componentManager
					.getByName(componentName);
			String category = componentLoader.getCategory();

			boolean valid = false;
			List<String> candidatesForDomain = new ArrayList<String>();

			Intent intentDomain = new Intent(domain);
			for (ResolveInfo availableCompInfo : applicationContext
					.getPackageManager().queryIntentServices(intentDomain, 0)) {
				if (availableCompInfo.serviceInfo.name.contains(componentName)) {
					// Component is working (registered to current domain)
					valid = true;
				} else {
					if (!componentManager
							.isLoaded(availableCompInfo.serviceInfo.name)) {
						// Here are stored all categories' components to current
						// domain
						candidatesForDomain
								.add(availableCompInfo.serviceInfo.name);
					}
				}
			}

			if (!valid) {
				List<String> candidatesForCategory = new ArrayList<String>();

				// Browse for same category components that are not loaded
				Intent intentCategory = new Intent(category);
				for (ResolveInfo availableCompInfo : applicationContext
						.getPackageManager().queryIntentServices(
								intentCategory, 0)) {
					if (!componentManager
							.isLoaded(availableCompInfo.serviceInfo.name)) {
						// Here are stored all domains' components to current
						// category
						candidatesForCategory
								.add(availableCompInfo.serviceInfo.name);
					}
				}

				String candidate = null;
				while (domain != null) {
					intentDomain = new Intent(domain);
					for (ResolveInfo availableCompInfo : applicationContext
							.getPackageManager().queryIntentServices(
									intentDomain, 0)) {
						if (candidate == null
								&& candidatesForCategory
										.contains(availableCompInfo.serviceInfo.name)) {
							candidate = availableCompInfo.serviceInfo.name;
							candidate = candidate.substring(0, candidate.lastIndexOf("Loader"));
						}
					}
					if (domain.indexOf(".") >= 0) {
						domain = domain.substring(0, domain.lastIndexOf("."));
					} else {
						domain = null;
					}
				}

				if (candidate != null) {
					Log.d(this.getClass().getName(), componentName
							+ " must be replaced by " + candidate);
					replaceComponents(componentName, candidate);
				} else {
					Log
							.d(this.getClass().getName(),
									"There is no candidate to replace "
											+ componentName);
				}
			}

		}
	}

	private void replaceComponents(String oldComponentName,
			String newComponentName) {

		ComponentState componentState = getComponentState(oldComponentName);

		for (ReceptacleInfo rec : componentState.getReceptacles()) {
			Log.d(this.getClass().getName(), "receptacle: " + rec.getName()
					+ ", provider: " + rec.getComponentName());
		}
		for (Entry<ServiceInfo, List<ReceptacleInfo>> entry : componentState
				.getServices().entrySet()) {

			Log.d(this.getClass().getName(), "service: "
					+ entry.getKey().getName());
			for (ReceptacleInfo rec : componentState.getReceptacles()) {
				Log.d(this.getClass().getName(), "connected to: "
						+ rec.getName() + ", consumer: "
						+ rec.getComponentName());
			}
		}

		// unload current component
		unloadComponent(oldComponentName, newComponentName);

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
				ReceptacleInfo receptacleInfo = loader
						.getReceptacleInfo(receptacleName);
				ServiceInfo connectedServiceInfo = receptacleInfo
						.getServiceInfo();

				Log.d(this.getClass().getName(), "receptacle " + receptacleName
						+ " is bound: " + receptacleInfo.isBound());

				if (connectedServiceInfo != null) {
					Log.d(this.getClass().getName(), "receptacle "
							+ receptacleName + " is connected to component "
							+ connectedServiceInfo.getComponentName());
					if (connectedServiceInfo.equals(componentName)) {
						// Service is provided by this component
						componentState.addReceptacle(receptacleInfo);
					}
				}
			}

			// Find out to each receptacles the services are bound
			List<String> componentNames = componentManager.getLoadedComponentNames();
			for (String name : componentNames) {
				loader = componentManager.getByName(name);

				receptacleNames = new ArrayList<String>();
				loader.getReceptacleNames(receptacleNames);
				for (String receptacleName : receptacleNames) {
					ReceptacleInfo receptacleInfo = loader
							.getReceptacleInfo(receptacleName);
					ServiceInfo connectedServiceInfo = receptacleInfo
							.getServiceInfo();
					if (connectedServiceInfo != null) {
						if (connectedServiceInfo.getComponentName().equals(
								componentName)) {
							Log.d(this.getClass().getName(), "receptacle "
									+ receptacleName
									+ " is connected to component "
									+ connectedServiceInfo.getComponentName());

							componentState.addService(connectedServiceInfo,
									receptacleInfo);
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
	 * Connects component's services and receptacles, based on a ComponentState
	 * object
	 * 
	 * @param componentName
	 * @param componentState
	 */
	private void setComponentState(String componentName,
			ComponentState componentState) {
		// After loading the component, it's necessary to re-build its state,
		// if there is one

	}

	private void unloadComponent(String componentName, String newComponentName) {
		try {
			ILocalLoader loader = componentManager.getByName(componentName);

			ComponentInfo internalState = loader.getInternalState();
			if (internalState != null) {
				componentsInfo.put(newComponentName, internalState);
			}
			
			// Notify the component
			loader.stop();

			// Disconnect all the components services and the component loader
			// service
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
			Log.d(this.getClass().getName(), "load component: " + callId + ", "
					+ componentName);
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
			Log.d(this.getClass().getName(), "componentLoaded: " + callId
					+ ", " + componentName);
			ILocalLoader loader = componentManager.getByName(componentName);
			ComponentInfo internalState = componentsInfo.get(componentName);
			if (internalState != null) {
				loader.setInternalState(internalState);
			}
			loader.start();
			requests.remove(callId);
		}
	}

}
