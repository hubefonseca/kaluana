package mobilis.app;

import java.util.ArrayList;
import java.util.List;

import mobilis.api.control.IComponentManager;
import mobilis.api.control.IComponentManagerListener;
import mobilis.examples.pingpong.PingComponent;
import mobilis.examples.pingpong.PongComponent;
import mobilis.impl.Receptacle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class Application extends Activity {

	private PingComponent pingComponent;
	private PongComponent pongComponent;

	private IComponentManager componentManager;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Relacionar as classes com nome e versão do componente
		// Component manager!!
		
		Intent intent = new Intent(mobilis.api.control.IComponentManager.class.getName());
		bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
		
	}
	
	public ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			try {
				componentManager = IComponentManager.Stub.asInterface(service);
				
				componentManager.init(mListener);
				List<String> componentNames = new ArrayList<String>();
				componentNames.add("mobilis.examples.pingpong.PingComponent");
				componentNames.add("mobilis.examples.pingpong.PongComponent");
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
	
	public IComponentManagerListener mListener = new IComponentManagerListener.Stub() {

		@Override
		public void componentsLoaded(long callId) throws RemoteException {
			Log.d(this.getClass().getName(), "callID: " + callId);
			
			IBinder servicePing = null;
			Receptacle recaptaclePing = null;
			Receptacle receptaclePong = null;
			IBinder servicePong = null;
			
			Log.d(this.getClass().getName(), "Connecting services on receptacles...");
			
			pingComponent = (PingComponent)componentManager.getComponent("mobilis.examples.pingpong.PingComponent");
			pongComponent = (PongComponent)componentManager.getComponent("mobilis.examples.pingpong.PongComponent");
			
			servicePing = pingComponent.getService("ping");
			recaptaclePing = (Receptacle)pingComponent.getReceptacle("pong");
			recaptaclePing.setContextWrapper(getThis());
			recaptaclePing.setBindListener(pingComponent);
			
			servicePong = pongComponent.getService("pong");
			receptaclePong = (Receptacle)pongComponent.getReceptacle("ping");
			receptaclePong.setContextWrapper(getThis());
			receptaclePong.setBindListener(pongComponent);
						
			receptaclePong.connectToService(servicePing);
			recaptaclePing.connectToService(servicePong);
		}
	};
	
	public Application getThis() {
		return this;
	}
}
