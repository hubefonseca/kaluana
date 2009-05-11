package mobilis.app;

import java.util.ArrayList;
import java.util.List;

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

public class NavigatorApp extends Activity implements IComponentManagerListener {

	private ILocalLoader navigatorLoader;
	private ILocalLoader locationProviderLoader;

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
				componentNames.add("mobilis.examples.navigator");
				componentNames.add("mobilis.context.location.LocationProviderComponent");
				componentManager.loadComponents(componentNames, 0);
				
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
	
	public NavigatorApp getThis() {
		return this;
	}

	@Override
	public void componentsLoaded(long callId) throws RemoteException {		
		navigatorLoader = componentManager.getComponent("mobilis.examples.navigator.NavigatorComponent");
		locationProviderLoader = componentManager.getComponent("mobilis.context.location.LocationProviderComponent");
		
		navigatorLoader.start();
		locationProviderLoader.start();
	}

	@Override
	public IBinder asBinder() { 
		return null;
	}
	
}
