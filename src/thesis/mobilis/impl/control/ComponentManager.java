package thesis.mobilis.impl.control;

import thesis.mobilis.api.IBindListener;
import thesis.mobilis.api.IComponent;
import thesis.mobilis.api.control.IComponentLoader;
import thesis.mobilis.api.control.IComponentManager;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

public class ComponentManager implements IComponentManager {

	private IComponentLoader componentLoader;
	
	private IBindListener listener;
	
	public ComponentManager(IBindListener listener) {
		this.listener = listener;
	}
	
	public ServiceConnection mServiceConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName name, IBinder service) {
			componentLoader = IComponentLoader.Stub.asInterface(service);
			
			try {
				listener.connected("ComponentLoader");
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
	public IComponent getComponent(String componentName) throws RemoteException {
		return componentLoader.getComponent(componentName);
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

}
