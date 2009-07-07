package kaluana.impl.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import kaluana.api.control.IComponentManager;
import kaluana.api.control.IComponentManagerListener;
import kaluana.api.control.ILocalLoader;
import kaluana.api.control.IRemoteLoader;
import kaluana.impl.adaptation.AdaptationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ComponentManager extends Service {

	private LoaderCollection loadedComponents = null;
	private IRemoteLoader remoteLoader = null;
	private Context applicationContext = null;
	private AdaptationManager adaptationManager = null;
	
	private boolean initialized = false;
	
	/**
	 * All requests and their requesters
	 */
	private HashMap<IComponentManagerListener, List<String>> requests;

	private final IComponentManager.Stub mComponentManager = new IComponentManager.Stub() {

		@Override
		public ILocalLoader getComponent(String category)
				throws RemoteException {
			ILocalLoader loader = loadedComponents.getByCategory(category);
			return loader;
		}

		
		/**
		 * This method receives a list with the category of each component that shall be loaded
		 */
		@Override
		public void loadComponents(List<String> categories, IComponentManagerListener listener)
				throws RemoteException {
			if (!initialized) {
				remoteLoader = new RemoteLoader(getContext(), this);
				loadedComponents = new LoaderCollection();
				requests = new HashMap<IComponentManagerListener, List<String>>();
				
				// Start Adaptation Manager
				adaptationManager = new AdaptationManager(getContext(), this);
				
				initialized = true;
			}
			
			requests.put(listener, categories);
			for (String category : categories) {
				Log.i(this.getClass().getName(), "loading: " + category + "...");
				remoteLoader.loadComponent(category);
			}
		}

		@Override
		public void loaded(ILocalLoader loader) throws RemoteException {
			loadedComponents.add(loader.getCategory(), loader);

			// Manage component dependencies
			List<String> dependencies = new ArrayList<String>();
			loader.getDependencies(dependencies);
			
			List<String> componentNames;
			IComponentManagerListener listener;
			for (String dependency : dependencies) {
				for (Entry<IComponentManagerListener, List<String>> entry : requests.entrySet()) {
					listener = entry.getKey();
					componentNames = entry.getValue();
					if (componentNames.contains(loader.getCategory()) && !componentNames.contains(dependency)) {
						remoteLoader.loadComponent(dependency);
						componentNames.add(dependency);
						requests.remove(listener);
						requests.put(listener, componentNames);
					}
				}
			}
			
			// Verify whether there are finished requests
			for (Entry<IComponentManagerListener, List<String>> entry : requests.entrySet()) {
				listener = entry.getKey();
				componentNames = entry.getValue();
				if (loadedComponents.isRequestFinished(componentNames)) {
					Log.d(this.getClass().getName(), "request is finished: notifying " + listener.getClass());
					listener.componentsLoaded(componentNames);
					requests.remove(listener);
				}
			}
		}

		@Override
		public void unloaded(String componentName) throws RemoteException {
			Log.i(this.getClass().getName(), "Component unloaded: " + componentName);
			loadedComponents.remove(componentName);
		}

		@Override
		public boolean isLoaded(String componentName) throws RemoteException {
			return loadedComponents.contains(componentName);
		}

		@Override
		public List<String> getLoadedComponentNames() throws RemoteException {
			return loadedComponents.getAllNames();
		}

		@Override
		public ILocalLoader getByName(String name) throws RemoteException {
			return loadedComponents.getByName(name);
		}

		@Override
		public void unloadComponent(String componentName)
				throws RemoteException {
			remoteLoader.unloadComponent(componentName);
		}
		
	};

	@Override
	public IBinder onBind(Intent intent) {
		return mComponentManager;
	}
	
	public Context getContext() {
		return (applicationContext != null) ? applicationContext : getApplicationContext();
	}
	
	public void setContext(Context context) {
		this.applicationContext = context;
	}
	
	public AdaptationManager getAdaptationManager() {
		return adaptationManager;
	}
	
}
