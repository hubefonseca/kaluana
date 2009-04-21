package mobilis.impl.control;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import mobilis.api.IComponent;
import mobilis.api.control.IComponentLoader;
import mobilis.api.control.IComponentLoaderListener;
import mobilis.api.control.IComponentManager;
import mobilis.api.control.IComponentManagerListener;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ComponentManager implements IComponentManager, IComponentLoaderListener {

	private ComponentCollection loadedComponents;
	
	private IComponentLoader componentLoader;
	private IComponentManagerListener componentManagerListener;
	
	private HashMap<Long, List<String>> callRequests;
	
	public ComponentManager(IComponentManagerListener listener) {
		this.componentManagerListener = listener;
		loadedComponents = new ComponentCollection();
		callRequests = new HashMap<Long, List<String>>();
	}
	
	public ServiceConnection mServiceConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName name, IBinder service) {
			componentLoader = IComponentLoader.Stub.asInterface(service);
			
			try {
				componentManagerListener.start();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void onServiceDisconnected(ComponentName name) {
			componentLoader = null;
		}

	};

	
	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void loadComponents(List<String> componentNames, long callId) throws RemoteException {
		callRequests.put(callId, componentNames);
		Iterator<String> iterator = componentNames.iterator();
		String componentName;
		while (iterator.hasNext()) {
			componentName = iterator.next();
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
		loadedComponents.remove(componentName);
	}

	@Override
	public IComponent getComponent(String componentName) throws RemoteException {
		return loadedComponents.getByName(componentName);
	}

}
