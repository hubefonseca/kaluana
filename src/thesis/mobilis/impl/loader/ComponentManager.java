package thesis.mobilis.impl.loader;

import thesis.mobilis.api.IBinderCallbackListener;
import thesis.mobilis.api.IComponent;
import thesis.mobilis.api.loader.IComponentLoader;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

public class ComponentManager {

	private IComponentLoader componentLoader;
	
	private IBinderCallbackListener listener;
	
	public ComponentManager(IBinderCallbackListener listener) {
		this.listener = listener;
	}
	
	public ServiceConnection mServiceConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName name, IBinder service) {
			componentLoader = IComponentLoader.Stub.asInterface(service);
			
			try {
				listener.bound("ComponentLoader");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void onServiceDisconnected(ComponentName name) {
			componentLoader = null;
		}

	};

	public IComponent getComponent(String componentName) throws RemoteException {
		return componentLoader.getComponent(componentName);
	}

}
