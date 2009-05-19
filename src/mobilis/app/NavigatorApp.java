package mobilis.app;

import java.util.ArrayList;
import java.util.List;

import mobilis.api.ReceptacleInfo;
import mobilis.api.ServiceInfo;
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

	private static final int CALL_ID = 5000;
	
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
				
				componentManager.init(getComponentManagerListener());
				List<String> componentNames = new ArrayList<String>();
				componentNames.add("mobilis.examples.navigator");
				componentNames.add("mobilis.context.location");
				componentManager.loadComponents(componentNames, CALL_ID);
				
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
	
	public NavigatorApp getComponentManagerListener() {
		return this;
	}

	@Override
	public void componentsLoaded(long callId) throws RemoteException {
		if (callId == CALL_ID) {
			navigatorLoader = componentManager.getComponent("mobilis.examples.navigator");
			locationProviderLoader = componentManager.getComponent("mobilis.context.location");
			
			try {
				IBinder semanticLocationProvider = locationProviderLoader.getService("semanticLocation");
				ServiceInfo semanticLocationProviderInfo = locationProviderLoader.getServiceInfo("semanticLocation");
				ReceptacleInfo semanticLocationReceptacleInfo = navigatorLoader.getReceptacleInfo("semanticLocation");
				
				navigatorLoader.bindReceptacle(semanticLocationReceptacleInfo, semanticLocationProvider, semanticLocationProviderInfo);
				
				navigatorLoader.start();
				locationProviderLoader.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public IBinder asBinder() { 
		return null;
	}
	
}
