package kaluana.app;

import java.util.ArrayList;
import java.util.List;

import kaluana.api.ReceptacleInfo;
import kaluana.api.ServiceInfo;
import kaluana.api.control.IComponentManager;
import kaluana.api.control.IComponentManagerListener;
import kaluana.api.control.IConfigService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class NavigatorApp extends Activity implements IComponentManagerListener {

	private IConfigService navigatorConfig;
	private IConfigService locationProviderConfig;

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
				
				List<String> componentsToLoad = new ArrayList<String>();
				componentsToLoad.add("kaluana.examples.navigator");
				componentsToLoad.add("kaluana.context.location");
				componentManager.loadComponents(componentsToLoad, getListener());
				
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
	
	public NavigatorApp getListener() {
		return this;
	}
	
	@Override
	public void componentsLoaded(List<String> components)
			throws RemoteException {
		navigatorConfig = componentManager.getComponent("kaluana.examples.navigator");
		locationProviderConfig = componentManager.getComponent("kaluana.context.location");
		
		try {
			IBinder semanticLocationProvider = locationProviderConfig.getService("semanticLocation");
			ServiceInfo semanticLocationProviderInfo = locationProviderConfig.getServiceInfo("semanticLocation");
			ReceptacleInfo semanticLocationReceptacleInfo = navigatorConfig.getReceptacleInfo("semanticLocation");
			
			navigatorConfig.bindReceptacle(semanticLocationReceptacleInfo, semanticLocationProvider, semanticLocationProviderInfo);
			
			navigatorConfig.start();
			locationProviderConfig.start();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public IBinder asBinder() { 
		return null;
	}
	
}
