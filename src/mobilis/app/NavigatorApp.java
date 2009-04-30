package mobilis.app;

import java.util.ArrayList;
import java.util.List;

import mobilis.api.control.IComponentManager;
import mobilis.api.control.IComponentManagerListener;
import mobilis.context.location.LocationProviderComponent;
import mobilis.examples.navigator.NavigatorComponent;
import mobilis.impl.adaptation.AdaptationManager;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class NavigatorApp extends Activity implements IComponentManagerListener {

	private NavigatorComponent navigatorComponent;
	private LocationProviderComponent locationProviderComponent;

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
				componentNames.add("mobilis.examples.navigator.NavigatorComponent");
				componentNames.add("mobilis.context.location.LocationProviderComponent");
				componentManager.loadComponents(componentNames, 0);
				
				
//				AdaptationManager adaptationManager = new AdaptationManager(getThis());
				
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
		navigatorComponent = (NavigatorComponent)componentManager.getComponent("mobilis.examples.navigator.NavigatorComponent");
		locationProviderComponent = (LocationProviderComponent)componentManager.getComponent("mobilis.context.location.LocationProviderComponent");
		
		navigatorComponent.setContextWrapper(getThis());
		
		navigatorComponent.start();		
		locationProviderComponent.start();
	}

	@Override
	public IBinder asBinder() { 
		return null;
	}
	
}
