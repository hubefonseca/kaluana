package thesis.mobilis.api.impl.loader;

import thesis.mobilis.api.Component;
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

	@Override
	public IBinder onBind(Intent arg0) {		
		return mComponentLoader;
	}

	private final thesis.mobilis.api.loader.ComponentLoader.Stub mComponentLoader = new thesis.mobilis.api.loader.ComponentLoader.Stub() {

		@Override
		public Component getComponent(String componentName)
				throws RemoteException {
			if (componentName.equals("ping"))
				return new thesis.mobilis.examples.helloworld.PingComponent();
			else 
				return new thesis.mobilis.examples.helloworld.PongComponent();
		}

		@Override
		public Component getComponentVersioned(String componentName,
				String componentVersion) throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}
    };

}
