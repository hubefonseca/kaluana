package mobilis.app;

import java.util.ArrayList;
import java.util.List;

import mobilis.api.IReceptacle;
import mobilis.api.IService;
import mobilis.api.control.IComponentManager;
import mobilis.api.control.IComponentManagerListener;
import mobilis.api.control.ILocalLoader;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class PingPongApp extends Activity implements IComponentManagerListener {

	private ILocalLoader pingComponentLoader;
	private ILocalLoader pongComponentLoader;

	private IComponentManager componentManager;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Intent intent = new Intent(mobilis.api.control.IComponentManager.class.getName());
		bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
		
	}
	
	public ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			try {
				componentManager = IComponentManager.Stub.asInterface(service);
				
				componentManager.init(getThis());
				List<String> componentNames = new ArrayList<String>();
				componentNames.add("mobilis.examples.ping");
				componentNames.add("mobilis.examples.pong");
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
		IBinder servicePing = null;
		IReceptacle recaptaclePing = null;
		IReceptacle receptaclePong = null;
		IBinder servicePong = null;
		
		pingComponentLoader = componentManager.getComponent("mobilis.examples.ping");
		pongComponentLoader = componentManager.getComponent("mobilis.examples.pong");
		
		servicePing = pingComponentLoader.getService("ping");
		recaptaclePing = pingComponentLoader.getReceptacle("pong");
		
		servicePong = pongComponentLoader.getService("pong");
		receptaclePong = pongComponentLoader.getReceptacle("ping");
//		
//		pingComponentLoader.bindReceptacle()
//		
		pingComponentLoader.start();
		pongComponentLoader.start();
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
