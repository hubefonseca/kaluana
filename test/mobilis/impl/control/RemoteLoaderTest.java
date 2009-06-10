package mobilis.impl.control;

import mobilis.api.control.IComponentManager;
import mobilis.api.control.IRemoteLoader;
import android.app.Service;
import android.content.Context;
import android.os.RemoteException;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public class RemoteLoaderTest extends ServiceTestCase<Service> {

	public RemoteLoaderTest(Class<Service> serviceClass) {
		super(serviceClass);
	}

	@SmallTest
	public void testRemoteLoader() {
		IComponentManager componentManager = null;
		Context context = getSystemContext();
		
		IRemoteLoader remoteLoader = new RemoteLoader(context, componentManager);
		
		try {
			remoteLoader.loadComponent("");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
