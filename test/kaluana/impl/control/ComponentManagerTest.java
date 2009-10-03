package kaluana.impl.control;

import java.util.ArrayList;
import java.util.List;

import kaluana.api.IContainer;
import kaluana.api.ReceptacleInfo;
import kaluana.api.ServiceInfo;
import kaluana.api.control.IComponentManager;
import kaluana.api.control.IComponentManagerListener;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

public class ComponentManagerTest extends ServiceTestCase<ComponentManager> {

	IComponentManager componentManager;
	
	private IContainer pingComponentConfig;
	private IContainer pongComponentConfig;
	
	public ComponentManagerTest() {
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
			componentNames.add("kaluana.examples.ping");
			componentNames.add("kaluana.examples.pong");
			componentManager.loadComponents(componentNames, new ComponentManagerListener());
			
			componentNames = new ArrayList<String>();
			componentNames.add("kaluana.examples.pong");
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
			
			pingComponentConfig = componentManager.getComponent("kaluana.examples.ping");
			pongComponentConfig = componentManager.getComponent("kaluana.examples.pong");
			
			assertNotNull("ping component loader is null", pingComponentConfig);
			assertNotNull("pong component loader is null", pongComponentConfig);
			
			IBinder pingService = pingComponentConfig.getService("ping");
			IBinder pongService = pongComponentConfig.getService("pong");

			assertNotNull("ping service is null", pingService);
			assertNotNull("pong service is null", pongService);
			
			ServiceInfo pingServiceInfo = pingComponentConfig.getServiceInfo("ping");
			ServiceInfo pongServiceInfo = pongComponentConfig.getServiceInfo("pong");
			
			assertNotNull("ping service info is null", pingServiceInfo);
			assertNotNull("pong service info is null", pongServiceInfo);
			
			ReceptacleInfo pingReceptacleInfo = pongComponentConfig.getReceptacleInfo("ping");
			ReceptacleInfo pongReceptacleInfo = pingComponentConfig.getReceptacleInfo("pong");
			
			assertNotNull("ping receptacle info is null", pingReceptacleInfo);
			assertNotNull("pong receptacle info is null", pongReceptacleInfo);
			
			pingComponentConfig.bindReceptacle(pongReceptacleInfo, pongService, pongServiceInfo);
			pongComponentConfig.bindReceptacle(pingReceptacleInfo, pingService, pingServiceInfo);
			
			pingComponentConfig.start();
			pongComponentConfig.start();
		}

		@Override
		public IBinder asBinder() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
