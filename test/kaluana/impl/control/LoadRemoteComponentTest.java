package kaluana.impl.control;

import java.util.ArrayList;
import java.util.List;

import kaluana.api.control.IComponentManager;
import kaluana.api.control.IComponentManagerListener;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

public class LoadRemoteComponentTest extends ServiceTestCase<ComponentManager> {

	IComponentManager componentManager;

	public LoadRemoteComponentTest() {
		super(ComponentManager.class);

		Intent intent = new Intent(kaluana.api.control.IComponentManager.class.getName());
		componentManager = IComponentManager.Stub.asInterface(bindService(intent));
	}

	@SmallTest
	public void testLoadComponents() {
		// Makes Component Manager point to a real context instance
		getService().setContext(getSystemContext());
		
		try {
			List<String> componentNames = new ArrayList<String>();
			
			componentNames = new ArrayList<String>();
			componentNames.add("kaluana.sdm");
			componentManager.loadComponents(componentNames, new ComponentManagerListener());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public class ComponentManagerListener implements IComponentManagerListener {

		@Override
		public void componentsLoaded(List<String> components) throws RemoteException {
			
			for (String c : components) {
				Log.i(this.getClass().getName(), "component loaded: " + c);
			}
		}

		@Override
		public IBinder asBinder() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
