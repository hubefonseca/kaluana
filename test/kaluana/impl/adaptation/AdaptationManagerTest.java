package kaluana.impl.adaptation;

import java.util.ArrayList;
import java.util.List;

import kaluana.api.control.IComponentManager;
import kaluana.api.control.IComponentManagerListener;
import kaluana.impl.control.ComponentManager;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public class AdaptationManagerTest extends ServiceTestCase<ComponentManager> {

	AdaptationManager adaptationManager;
	IComponentManager componentManager;
	
	public AdaptationManagerTest() {
		super(ComponentManager.class);
		
		Intent intent = new Intent(kaluana.api.control.IComponentManager.class.getName());
		componentManager = IComponentManager.Stub.asInterface(bindService(intent));
	}
	
	@SmallTest
	public void testAlgorithm() {
		// Makes Component Manager point to a real context instance
		getService().setContext(getSystemContext());
		try {
			componentManager.init(new ComponentManagerListener());
			List<String> componentNames = new ArrayList<String>();
			componentNames.add("mobilis.examples.navigator");
			componentManager.loadComponents(componentNames, 123123);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			adaptationManager = getService().getAdaptationManager();
			
			kaluana.context.Context context = new kaluana.context.Context();
			context.setLatitude(-43);
			context.setLongitude(-22);
			context.setLocation("loc.br.rio.puc");
			
			adaptationManager.onContextChange(context);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public class ComponentManagerListener implements IComponentManagerListener {

		@Override
		public void componentsLoaded(long callId) throws RemoteException {

		}

		@Override
		public IBinder asBinder() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
