package mobilis.impl.adaptation;

import java.util.List;

import mobilis.api.adaptation.IAdaptable;
import mobilis.api.adaptation.IAdaptationManager;
import mobilis.context.IProviderService;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AdaptationManager implements IAdaptationManager {

	private IProviderService contextProvider;
	
	private List<IAdaptable> components;
	
	public AdaptationManager(ContextWrapper contextWrapper) {
		Intent intent = new Intent(mobilis.context.IProviderService.class.getName());
		contextWrapper.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
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
		
		// Verify whether the current components can still operate well
		
		
		// Search for new components that could operate under the new conditions
		
		
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
