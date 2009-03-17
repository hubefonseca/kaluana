package thesis.mobilis;

import thesis.mobilis.api.IBinderCallbackListener;
import thesis.mobilis.api.loader.IComponentLoader;
import thesis.mobilis.examples.pingpong.PingComponent;
import thesis.mobilis.examples.pingpong.PongComponent;
import thesis.mobilis.impl.Receptacle;
import thesis.mobilis.impl.loader.ComponentLoader;
import thesis.mobilis.impl.loader.ComponentManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class Application extends Activity implements IBinderCallbackListener {

	private PingComponent pingComponent;
	private PongComponent pongComponent;

	private ComponentManager componentManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Relacionar as classes com nome e versão do componente
		// Component manager!!
	

		//		Intent intent = new Intent(thesis.mobilis.api.impl.loader.ComponentLoader.class.getName());

		componentManager = new ComponentManager(this);
		
		Intent intent = new Intent(thesis.mobilis.api.loader.IComponentLoader.class.getName());
		bindService(intent, componentManager.mServiceConnection, Context.BIND_AUTO_CREATE);
		
	}

	@Override
	public void bound(String name) throws RemoteException {
		Log.d(this.getClass().getName(), "bound: " + name + "!!!!");
		if (name.equals("ComponentLoader")) {
			try {
				
				pongComponent = (PongComponent)componentManager.getComponent("PongComponent");
				pingComponent = (PingComponent)componentManager.getComponent("PingComponent");
			
				IBinder b = pingComponent.getService("ping");
				Receptacle r = (Receptacle)pongComponent.getReceptacle("ping");
				r.setContextWrapper(this);
				r.setBinderCallbackListener(this);
				r.connectToService(b);
			
				IBinder d = pongComponent.getService("pong");
				Receptacle r2 = (Receptacle)pingComponent.getReceptacle("pong");
				r2.setContextWrapper(this);
				r2.setBinderCallbackListener(this);
				r2.connectToService(d);
			
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (name.equals("ping")) {
			pongComponent.start();
		} else if (name.equals("pong")) {
			pingComponent.start();
		}
	}
	
	@Override
	public void unbound(String name) throws RemoteException {
		Log.d(this.getClass().getName(), "Component " + name + " released!");
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}