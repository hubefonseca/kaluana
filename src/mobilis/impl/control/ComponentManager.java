package mobilis.impl.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mobilis.api.control.IComponentManager;
import mobilis.api.control.IComponentManagerListener;
import mobilis.api.control.ILocalLoader;
import mobilis.api.control.IRemoteLoader;
import mobilis.impl.adaptation.AdaptationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ComponentManager extends Service {

	private LoaderCollection loadedComponents;
	
	private IRemoteLoader remoteLoader;
	
	private List<IComponentManagerListener> listeners;
	
	/**
	 * This structure stores, for each request to load components, the components
	 * that the caller wants to load
	 */
	private HashMap<Long, List<String>> requests;

	private final IComponentManager.Stub mComponentManager = new IComponentManager.Stub() {

		@Override
		public void init(IComponentManagerListener listener) 
				throws RemoteException {
			remoteLoader = new RemoteLoader(getContextWrapper(), this);
			loadedComponents = new LoaderCollection();
			requests = new HashMap<Long, List<String>>();

			// Adds the component manager listener to the listeners list
			listeners = new ArrayList<IComponentManagerListener>();
			listeners.add(listener);
			
			// Start Adaptation Manager
			AdaptationManager adaptationManager = new AdaptationManager(getContextWrapper(), this);
		}
		
		@Override
		public ILocalLoader getComponent(String category)
				throws RemoteException {
			ILocalLoader loader = loadedComponents.getByCategory(category);
			return loader;
		}

		@Override
		/**
		 * This method receives a list with the category of each component that shall be loaded
		 */
		public void loadComponents(List<String> categories, long callId)
				throws RemoteException {
			requests.put(callId, categories);
			for (String category : categories) {
				Log.i(this.getClass().getName(), "loading: " + category);
				remoteLoader.loadComponent(category);
			}
		}

		@Override
		public void loaded(ILocalLoader loader) throws RemoteException {
			
			loadedComponents.add(loader.getCategory(), loader);
			
//			// Início debug
//			Log.i(this.getClass().getName(), "adding: " + loader.getName());
//			for (String cat : loadedComponents.keySet()) {
//				Log.d(this.getClass().getName(), "category: " + cat);
//				List<ILocalLoader> l = loadedComponents.get(cat);
//				for (ILocalLoader lo : l) {
//					Log.d(this.getClass().getName(), "loader: " + lo.getName());
//				}
//			}
//			// Fim debug
			
			
			// Manage component dependencies before deliver it to caller
			
			
			// Verify if any request is finished
			for (Long request : requests.keySet()) {
				List<String> componentNames = requests.get(request);
				if (loadedComponents.isRequestFinished(componentNames)) {
					Log.d(this.getClass().getName(), "request " + request + " is finished!");
					for (IComponentManagerListener listener : listeners) {
						listener.componentsLoaded(request);
					}
					requests.remove(request);
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
		public List<String> getAllNames() throws RemoteException {
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

		@Override
		public void addListener(IComponentManagerListener listener)
				throws RemoteException {
			listeners.add(listener);
		}
		
	};

	@Override
	public IBinder onBind(Intent intent) {
		return mComponentManager;
	}
	
	private ComponentManager getContextWrapper() {
		return this;
	}

}
