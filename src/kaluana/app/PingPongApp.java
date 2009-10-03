package kaluana.app;

import java.util.ArrayList;
import java.util.List;

import kaluana.api.IContainer;
import kaluana.api.ReceptacleInfo;
import kaluana.api.ServiceInfo;
import kaluana.api.control.IComponentManager;
import kaluana.api.control.IComponentManagerListener;
import kaluana.examples.pingpong.PingComponent;
import kaluana.examples.pingpong.PongComponent;
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

	private IContainer pingComponentContainer;
	private IContainer pongComponentContainer;

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
				
				// Application should not know the components name or implementation
				// and should search for a component by other criteria than its name
				// This demo is for example purpose only
				List<String> componentNames = new ArrayList<String>();
				componentNames.add(PingComponent.class.getName());
				componentNames.add(PongComponent.class.getName());
				componentManager.loadComponents(componentNames, getListener());
				
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
	
	public PingPongApp getListener() {
		return this;
	}

	@Override
	public void componentsLoaded(List<String> components)
			throws RemoteException {
		Log.d(this.getClass().getName(), "components loaded!");
		
		pingComponentContainer = componentManager.getComponent("kaluana.examples.pingpong.PingComponent");
		pongComponentContainer = componentManager.getComponent("kaluana.examples.pingpong.PongComponent");
		
		IBinder pingService = pingComponentContainer.getService("ping");
		IBinder pongService = pongComponentContainer.getService("pong");

		ServiceInfo pingServiceInfo = pingComponentContainer.getServiceInfo("ping");
		ServiceInfo pongServiceInfo = pongComponentContainer.getServiceInfo("pong");
		
		ReceptacleInfo pingReceptacleInfo = pongComponentContainer.getReceptacleInfo("ping");
		ReceptacleInfo pongReceptacleInfo = pingComponentContainer.getReceptacleInfo("pong");
		
		pingComponentContainer.bindReceptacle(pongReceptacleInfo, pongService, pongServiceInfo);
		pongComponentContainer.bindReceptacle(pingReceptacleInfo, pingService, pingServiceInfo);
		
		pingComponentContainer.start();
		pongComponentContainer.start();	
	}
	
	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
