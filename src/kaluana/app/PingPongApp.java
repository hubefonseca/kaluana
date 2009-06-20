package kaluana.app;

import java.util.ArrayList;
import java.util.List;

import kaluana.api.ReceptacleInfo;
import kaluana.api.ServiceInfo;
import kaluana.api.control.IComponentManager;
import kaluana.api.control.IComponentManagerListener;
import kaluana.api.control.ILocalLoader;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class PingPongApp extends Activity implements IComponentManagerListener {

	private ILocalLoader pingComponentLoader;
	private ILocalLoader pongComponentLoader;

	private IComponentManager componentManager;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Intent intent = new Intent(kaluana.api.control.IComponentManager.class.getName());
		bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
	}
	
	public ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			try {
				componentManager = IComponentManager.Stub.asInterface(service);
				
				componentManager.init(getThis());
				List<String> componentNames = new ArrayList<String>();
				componentNames.add("kaluana.examples.ping");
				componentNames.add("kaluana.examples.pong");
				componentManager.loadComponents(componentNames, 123123);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	public PingPongApp getThis() {
		return this;
	}

	@Override
	public void componentsLoaded(long callId) throws RemoteException {
		
		pingComponentLoader = componentManager.getComponent("kaluana.examples.ping");
		pongComponentLoader = componentManager.getComponent("kaluana.examples.pong");
		
		IBinder pingService = pingComponentLoader.getService("ping");
		IBinder pongService = pongComponentLoader.getService("pong");

		ServiceInfo pingServiceInfo = pingComponentLoader.getServiceInfo("ping");
		ServiceInfo pongServiceInfo = pongComponentLoader.getServiceInfo("pong");
		
		ReceptacleInfo pingReceptacleInfo = pongComponentLoader.getReceptacleInfo("ping");
		ReceptacleInfo pongReceptacleInfo = pingComponentLoader.getReceptacleInfo("pong");
		
		Log.d(this.getClass().getName(), "ping service info: " + pingServiceInfo);
		Log.d(this.getClass().getName(), "pong service info: " + pongServiceInfo);
		
		Log.d(this.getClass().getName(), "ping service: " + pingService);
		Log.d(this.getClass().getName(), "pong service: " + pongService);
		
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
