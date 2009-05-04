package mobilis.impl.control;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import mobilis.api.IComponent;
import mobilis.api.control.IComponentManager;
import mobilis.api.control.IComponentManagerListener;
import mobilis.api.control.IRemoteLoader;
import mobilis.impl.adaptation.AdaptationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ComponentManager extends Service {

	private ComponentCollection loadedComponents;
	
	private IRemoteLoader componentLoader;
	private IComponentManagerListener componentManagerListener;
	
	private HashMap<Long, List<String>> callRequests;

	private final IComponentManager.Stub mComponentManager = new IComponentManager.Stub() {

		@Override
		public IComponent getComponent(String componentName)
				throws RemoteException {
			return loadedComponents.getByName(componentName);
		}

		@Override
		public void loadComponents(List<String> componentNames, long callId)
				throws RemoteException {
			callRequests.put(callId, componentNames);
			for (String componentName : componentNames) {
				componentLoader.loadComponent(componentName, this);
			}
		}

		@Override
		public void loaded(IComponent component) throws RemoteException {
			Log.d(this.getClass().getName(), "Registering component's services and receptacles...");
			
			component.registerReceptacles();
			component.registerServices();
			
			// Manage component dependencies before deliver it to caller
			
			loadedComponents.add(component);
			
			Iterator<Entry<Long, List<String>>> iteratorCalls = callRequests.entrySet().iterator();
			Entry<Long, List<String>> entry;
			List<String> componentNames;
			long callId;
			while (iteratorCalls.hasNext()) {
				entry = iteratorCalls.next();
				componentNames = entry.getValue();
				callId = entry.getKey();
				Iterator<String> iteratorNames = componentNames.iterator();
				String name;
				boolean requestComplete = true;
				while (iteratorNames.hasNext()) {
					name = iteratorNames.next();
					if (!loadedComponents.contains(name)) {
						requestComplete = false;
						break;
					}
				}
				if (requestComplete) {
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
		public void init(IComponentManagerListener listener) 
				throws RemoteException {
			componentLoader = new ComponentLoader(getThis());
			loadedComponents = new ComponentCollection();
			callRequests = new HashMap<Long, List<String>>();
			componentManagerListener = listener;
			
			// Start Adaptation Manager
			AdaptationManager adaptationManager = new AdaptationManager(getThis());
			
		}

		@Override
		public void getLoadedComponents(List<String> componentNames)
				throws RemoteException {
			componentNames = loadedComponents.getComponentNames();
		}
		
	};

	@Override
	public IBinder onBind(Intent intent) {
		return mComponentManager;
	}
	
	public ComponentManager getThis() {
		return this;
	}

}
