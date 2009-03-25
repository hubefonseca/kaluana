package thesis.mobilis;

import java.util.ArrayList;
import java.util.List;

import thesis.mobilis.api.IComponent;
import thesis.mobilis.api.control.IComponentLoaderListener;
import thesis.mobilis.api.control.IComponentManagerListener;
import thesis.mobilis.examples.pingpong.PingComponent;
import thesis.mobilis.examples.pingpong.PongComponent;
import thesis.mobilis.impl.Receptacle;
import thesis.mobilis.impl.control.ComponentManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class Application extends Activity implements IComponentManagerListener, IComponentLoaderListener {

	
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
		
		Intent intent = new Intent(thesis.mobilis.api.control.IComponentLoader.class.getName());
		bindService(intent, componentManager.mServiceConnection, Context.BIND_AUTO_CREATE);
		
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loaded(IComponent component) throws RemoteException {		
		IBinder b = null;
		Receptacle r2 = null;
		Receptacle r = null;
		IBinder d = null;
		
		List<String> loadedComponents = new ArrayList<String>();
		componentManager.getLoadedComponents(loadedComponents);
		
		if (loadedComponents.contains("PongComponent") && loadedComponents.contains("PingComponent")) {
			Log.d(this.getClass().getName(), "Connecting receptacles to services...");
			
			pingComponent = (PingComponent)componentManager.getComponent("PingComponent");
			pongComponent = (PongComponent)componentManager.getComponent("PongComponent");
			
			b = pingComponent.getService("ping");
			r2 = (Receptacle)pingComponent.getReceptacle("pong");
			r2.setContextWrapper(this);
			r2.setBindListener(pingComponent);
			
			d = pongComponent.getService("pong");
			r = (Receptacle)pongComponent.getReceptacle("ping");
			r.setContextWrapper(this);
			r.setBindListener(pongComponent);
						
			r.connectToService(b);
			r2.connectToService(d);
		}
	}

	@Override
	public void unloaded(IComponent component) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() throws RemoteException {		
		componentManager.loadComponent("PongComponent", this);
		componentManager.loadComponent("PingComponent", this);
	}
	
}