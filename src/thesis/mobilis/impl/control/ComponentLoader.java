package thesis.mobilis.impl.control;

import thesis.mobilis.api.IComponent;
import thesis.mobilis.api.control.IComponentLoader;
import thesis.mobilis.examples.pingpong.PingComponent;
import thesis.mobilis.examples.pingpong.PongComponent;
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
		public IComponent getComponent(String componentName)
				throws RemoteException {
			
			if (componentName.equals("PingComponent"))
				return new PingComponent();
			else
				return new PongComponent();
		}

		@Override
		public IComponent getComponentVersioned(String componentName,
				String componentVersion) throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

    };

	@Override
	public IBinder onBind(Intent intent) {
		return mComponentLoader;
	}
	
}

