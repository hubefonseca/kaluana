package kaluana.impl.control;

import java.util.ArrayList;
import java.util.List;

import kaluana.api.ReceptacleInfo;
import kaluana.api.ServiceInfo;
import kaluana.api.control.IComponentManager;
import kaluana.api.control.IComponentManagerListener;
import kaluana.api.control.ILocalLoader;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public class ComponentManagerTest extends ServiceTestCase<ComponentManager> {

	IComponentManager componentManager;
	
	private ILocalLoader pingComponentLoader;
	private ILocalLoader pongComponentLoader;
	
	public ComponentManagerTest() {
		super(ComponentManager.class);
		
		Intent intent = new Intent(kaluana.api.control.IComponentManager.class.getName());
		componentManager = IComponentManager.Stub.asInterface(bindService(intent));
	}
	
	@SmallTest
	public void testInit() {
		// Makes Component Manager point to a real context instance
		getService().setContext(getSystemContext());
		
		try {
			componentManager.init(new ComponentManagerListener());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SmallTest
	public void testLoadComponents() {
		// Makes Component Manager point to a real context instance
		getService().setContext(getSystemContext());
		
		try {
			componentManager.init(new ComponentManagerListener());
			List<String> componentNames = new ArrayList<String>();
			componentNames.add("kaluana.examples.ping");
			componentNames.add("kaluana.examples.pong");
			componentManager.loadComponents(componentNames, 123123);
			
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
		public void componentsLoaded(long callId) throws RemoteException {
			pingComponentLoader = componentManager.getComponent("kaluana.examples.ping");
			pongComponentLoader = componentManager.getComponent("kaluana.examples.pong");
			
			assertNotNull("ping component loader is null", pingComponentLoader);
			assertNotNull("pong component loader is null", pongComponentLoader);
			
			IBinder pingService = pingComponentLoader.getService("ping");
			IBinder pongService = pongComponentLoader.getService("pong");

			assertNotNull("ping service is null", pingService);
			assertNotNull("pong service is null", pongService);
			
			ServiceInfo pingServiceInfo = pingComponentLoader.getServiceInfo("ping");
			ServiceInfo pongServiceInfo = pongComponentLoader.getServiceInfo("pong");
			
			assertNotNull("ping service info is null", pingServiceInfo);
			assertNotNull("pong service info is null", pongServiceInfo);
			
			ReceptacleInfo pingReceptacleInfo = pongComponentLoader.getReceptacleInfo("ping");
			ReceptacleInfo pongReceptacleInfo = pingComponentLoader.getReceptacleInfo("pong");
			
			assertNotNull("ping receptacle info is null", pingReceptacleInfo);
			assertNotNull("pong receptacle info is null", pongReceptacleInfo);
			
			pingComponentLoader.bindReceptacle(pongReceptacleInfo, pongService, pongServiceInfo);
			pongComponentLoader.bindReceptacle(pingReceptacleInfo, pingService, pingServiceInfo);
			
			pingComponentLoader.start();
			pongComponentLoader.start();
		}

		@Override
		public IBinder asBinder() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
