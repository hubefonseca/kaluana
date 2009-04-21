package mobilis.app;

import java.util.ArrayList;
import java.util.List;

import mobilis.api.control.IComponentManagerListener;
import mobilis.examples.pingpong.PingComponent;
import mobilis.examples.pingpong.PongComponent;
import mobilis.impl.Receptacle;
import mobilis.impl.control.ComponentManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class Application extends Activity implements IComponentManagerListener {

	private PingComponent pingComponent;
	private PongComponent pongComponent;

	private ComponentManager componentManager;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Relacionar as classes com nome e versão do componente
		// Component manager!!

		componentManager = new ComponentManager(this);
		
		Intent intent = new Intent(mobilis.api.control.IComponentLoader.class.getName());
		bindService(intent, componentManager.mServiceConnection, Context.BIND_AUTO_CREATE);
		
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

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
		recaptaclePing.setContextWrapper(this);
		recaptaclePing.setBindListener(pingComponent);
		
		servicePong = pongComponent.getService("pong");
		receptaclePong = (Receptacle)pongComponent.getReceptacle("ping");
		receptaclePong.setContextWrapper(this);
		receptaclePong.setBindListener(pongComponent);
					
		receptaclePong.connectToService(servicePing);
		recaptaclePing.connectToService(servicePong);
	}

	@Override
	public void start() throws RemoteException {
		List<String> componentNames = new ArrayList<String>();
		componentNames.add("mobilis.examples.pingpong.PingComponent");
		componentNames.add("mobilis.examples.pingpong.PongComponent");
		componentManager.loadComponents(componentNames, 123123);
	}
	
}
