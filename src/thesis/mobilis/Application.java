package thesis.mobilis;

import thesis.mobilis.api.IBinderCallbackListener;
import thesis.mobilis.api.IReceptacle;
import thesis.mobilis.examples.helloworld.PingComponent;
import thesis.mobilis.examples.helloworld.PongComponent;
import thesis.mobilis.impl.loader.ComponentLoader;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Application extends Activity implements IBinderCallbackListener {

	private PingComponent pingComponent;
	private PongComponent pongComponent;

	private ComponentLoader componentLoader;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Relacionar as classes com nome e versão do componente

		pongComponent = new PongComponent(this);

		//		Intent intent = new Intent(thesis.mobilis.api.impl.loader.ComponentLoader.class.getName());

		Intent intent = new Intent(thesis.mobilis.examples.helloworld.services.iMobService.class.getName());
		bindService(intent, pongComponent.mServiceConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void bound() throws RemoteException {
		String a = "thesis.mobilis.examples.helloworld.services.iMobService";
		
		IBinder b = pongComponent.getService(a);
		
		pingComponent = new PingComponent(this);
		IReceptacle r = pingComponent.getReceptacle(a);
		
		r.connectToService(b);
		
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

}