package mobilis.impl.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
	private IComponentManagerListener componentManagerListener;
	
	/**
	 * This structure stores, for each request to load components, the components
	 * that the caller wants to load
	 */
	private HashMap<Long, List<String>> callRequests;

	private final IComponentManager.Stub mComponentManager = new IComponentManager.Stub() {

		@Override
		public void init(IComponentManagerListener listener) 
				throws RemoteException {
			remoteLoader = new RemoteLoader(getContextWrapper());
			loadedComponents = new LoaderCollection();
			callRequests = new HashMap<Long, List<String>>();
			componentManagerListener = listener;
			
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
		 * This method receives a list with the category of each component that has to be loaded
		 */
		public void loadComponents(List<String> categories, long callId)
				throws RemoteException {
			callRequests.put(callId, categories);
			for (String category : categories) {
				remoteLoader.loadComponent(category, this);
			}
		}

		@Override
		public void loaded(ILocalLoader loader) throws RemoteException {
			
			// Manage component dependencies before deliver it to caller
			loadedComponents.add(loader.getCategory(), loader);
			
			List<String> componentNames;
			long callId;
			for (Entry<Long, List<String>> entry : callRequests.entrySet()) {
				componentNames = entry.getValue();
				callId = entry.getKey();
				boolean requestComplete = true;
				for (String name : componentNames) {
					if (!loadedComponents.containsCategory(name) && !loadedComponents.contains(name)) {
						requestComplete = false;
						break;
					}
				}
				if (requestComplete) {
					Log.d(this.getClass().getName(), "request complete");
					componentManagerListener.componentsLoaded(callId);
					callRequests.remove(callId);
				}
			}
		}

		@Override
		public void unloaded(String componentName) throws RemoteException {
			// TODO Auto-generated method stub
			
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
			Log.d(this.getClass().getName(), componentName + " unload requested");			
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
