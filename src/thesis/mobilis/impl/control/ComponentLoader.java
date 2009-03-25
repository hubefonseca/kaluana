package thesis.mobilis.impl.control;

import thesis.mobilis.api.control.IComponentLoader;
import thesis.mobilis.api.control.IComponentLoaderListener;
import thesis.mobilis.examples.pingpong.PingComponent;
import thesis.mobilis.examples.pingpong.PongComponent;
import thesis.mobilis.impl.Component;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * The relation between different components must be stored
 * @author Hubert
 *
 */
public class ComponentLoader extends Service {
		
	private final IComponentLoader.Stub mComponentLoader = new IComponentLoader.Stub() {

		@Override
		public void loadBestComponent(String contextRepresentation,
				IComponentLoaderListener listener) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void loadComponent(String componentName,
				IComponentLoaderListener listener) throws RemoteException {
			
			Component component = null;
			
			if (componentName.equals("PingComponent")) {
				component = new PingComponent();
			} else {
				component = new PongComponent();
			}
			
			component.setName(componentName);
			listener.loaded(component);
		}

    };

	@Override
	public IBinder onBind(Intent intent) {
		return mComponentLoader;
	}
	
}

