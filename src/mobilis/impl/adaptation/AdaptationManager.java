package mobilis.impl.adaptation;

import mobilis.api.adaptation.IAdaptationManager;
import mobilis.context.location.ILocationListener;
import mobilis.context.location.IProviderService;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AdaptationManager implements IAdaptationManager, ILocationListener {

	IProviderService locationProvider;
	
	public AdaptationManager(ContextWrapper contextWrapper) {
		Intent intent = new Intent(mobilis.context.location.IProviderService.class.getName());
		contextWrapper.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
	}
	
	public ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			
			locationProvider = IProviderService.Stub.asInterface(service);
			
			Log.d(this.getClass().getName(), "Location provider service connected!");
			
			try {
				locationProvider.registerListener(getThis());
			} catch(RemoteException e) {
				
			}
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	@Override
	public void notifyContextChange() throws RemoteException {
		// TODO Auto-generated method stub
		
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
	public int locationChanged() throws RemoteException {
		Log.d(this.getClass().getName(), "User location has changed!!");
		
		
		return 0;
	}
}
