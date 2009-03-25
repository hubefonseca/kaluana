package thesis.mobilis.impl.control;

import java.util.List;

import thesis.mobilis.api.IComponent;
import thesis.mobilis.api.control.IComponentLoader;
import thesis.mobilis.api.control.IComponentLoaderListener;
import thesis.mobilis.api.control.IComponentManager;
import thesis.mobilis.api.control.IComponentManagerListener;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ComponentManager implements IComponentManager, IComponentLoaderListener {

	private ComponentCollection loadedComponents;
	
	private IComponentLoader componentLoader;
	private IComponentManagerListener componentManagerListener;
	private IComponentLoaderListener componentLoaderListener;
	
	public ComponentManager(IComponentManagerListener listener) {
		this.componentManagerListener = listener;
		loadedComponents = new ComponentCollection();
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
	public void loadComponent(String componentName, IComponentLoaderListener listener) throws RemoteException {
		this.componentLoaderListener = listener;
		componentLoader.loadComponent(componentName, this);
	}


	@Override
	public void loaded(IComponent component) throws RemoteException {
		Log.d(this.getClass().getName(), "Registering component's services and receptacles...");
		
		component.registerReceptacles();
		component.registerServices();
		
		// Manage component dependencies before deliver it to caller
		
		// Manage which components are already started		
		loadedComponents.add(component);
		
		// Deliver component to caller
		componentLoaderListener.loaded(component);
	}


	@Override
	public void unloaded(IComponent component) throws RemoteException {
		Log.d(this.getClass().getName(), "Component unloaded");
	}


	@Override
	public void getLoadedComponents(List<String> componentNames)
			throws RemoteException {
		loadedComponents.list(componentNames);
	}


	@Override
	public IComponent getComponent(String componentName) throws RemoteException {
		return loadedComponents.getByName(componentName);
	}

}
