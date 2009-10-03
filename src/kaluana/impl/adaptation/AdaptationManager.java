package kaluana.impl.adaptation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import kaluana.api.IContainer;
import kaluana.api.ReceptacleInfo;
import kaluana.api.ServiceInfo;
import kaluana.api.adaptation.IAdaptationManager;
import kaluana.api.control.IComponentManager;
import kaluana.api.control.IComponentManagerListener;
import kaluana.context.IProviderService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AdaptationManager implements IAdaptationManager,
		IComponentManagerListener {

	private Context applicationContext;
	private IProviderService contextProvider;
	private IComponentManager.Stub componentManager;

	private HashMap<String, InternalState> componentsInfo = new HashMap<String, InternalState>();

	/**
	 * Creates an adaptation manager
	 */
	public AdaptationManager(Context androidContextWrapper,
			IComponentManager.Stub componentManager) {
		this.componentManager = componentManager;
		this.applicationContext = androidContextWrapper;

		Intent intent = new Intent(kaluana.context.IProviderService.class.getName());
		this.applicationContext.bindService(intent, mServiceConnection,	Context.BIND_AUTO_CREATE);
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
	 * TODO: the old adaptation mechanism should be replaced by a component
	 * based adaptation manager
	 */
	@Override
	public void onContextChange(kaluana.context.Context context)
			throws RemoteException {
		
	}

	private void replaceComponents(String oldComponentName,
			String newComponentName) {

		ExternalState componentState = getComponentState(oldComponentName);

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

	private ExternalState getComponentState(String componentName) {
		ExternalState componentState = new ExternalState();
		try {
			IContainer loader = componentManager.getComponent(componentName);

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
			List<String> componentNames = componentManager
					.getLoadedComponentNames();
			for (String name : componentNames) {
				loader = componentManager.getComponent(name);

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
			ExternalState componentState) {
		// After loading the component, it's necessary to re-build its state,
		// if there is one

	}

	private void unloadComponent(String componentName, String newComponentName) {
		try {
			IContainer loader = componentManager.getComponent(componentName);

			InternalState internalState = loader.getInternalState();
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
			
			List<String> componentNames = new ArrayList<String>();
			componentNames.add(componentName);

			componentManager.loadComponents(componentNames, getListener());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public IBinder asBinder() {
		// No usage.
		return null;
	}

	private IComponentManagerListener getListener() {
		return this;
	}
	
	private AdaptationManager getContextWrapper() {
		return this;
	}

	@Override
	public void componentsLoaded(List<String> components) throws RemoteException {
		
		for (String componentName : components) {
			Log.d(this.getClass().getName(), "component " + componentName + " is loaded");
			IContainer loader = componentManager.getComponent(componentName);
			InternalState internalState = componentsInfo.get(componentName);
			if (internalState != null) {
				loader.setInternalState(internalState);
			}
			loader.start();
		}
	}

}
