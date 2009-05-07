package mobilis.impl.adaptation;

import java.util.ArrayList;
import java.util.List;

import mobilis.api.adaptation.IAdaptationManager;
import mobilis.api.control.IComponentManager;
import mobilis.context.IProviderService;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AdaptationManager implements IAdaptationManager {

	private ContextWrapper contextWrapper;
	private IProviderService contextProvider;
	private IComponentManager.Stub componentManager;
	
	public AdaptationManager(ContextWrapper contextWrapper, IComponentManager.Stub componentManager) {
		this.componentManager = componentManager;
		this.contextWrapper = contextWrapper;
		
		Intent intent = new Intent(mobilis.context.IProviderService.class.getName());
		this.contextWrapper.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
	}
	
	public ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			
			contextProvider = IProviderService.Stub.asInterface(service);
			
			Log.d(this.getClass().getName(), "Location provider service connected!");
			
			try {
				contextProvider.registerAdaptationManager(getThis());
				contextProvider.start();
			} catch(RemoteException e) {
				
			}
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	@Override
	public void onContextChange(mobilis.context.Context context) throws RemoteException {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "Context change notification!");
		Log.d(this.getClass().getName(), "new latitude: " + context.getLatitude());
		Log.d(this.getClass().getName(), "new longitude: " + context.getLongitude());
		Log.d(this.getClass().getName(), "new place: " + context.getLocation());
		
		// Search for components that could operate under the new conditions
		List<String> components = new ArrayList<String>();
		componentManager.getLoadedComponents(components);
		for (String component : components) {
			Log.d(this.getClass().getName(), "loaded component: " + component);
		}
		
		// Verify whether these components can still operate well
		Intent intent = new Intent(context.getLocation());
		for (ResolveInfo info : contextWrapper.getPackageManager().queryIntentServices(intent, 0)) {
			Log.d(this.getClass().getName(), "service: " + info.serviceInfo.name);
		}
		
		
		
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	public AdaptationManager getThis() {
		return this;
	}

	@Override
	public void registerComponent(String scope) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}
