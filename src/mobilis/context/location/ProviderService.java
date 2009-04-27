package mobilis.context.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ProviderService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return mProviderImpl;
	}

	private final IProviderService.Stub mProviderImpl = new IProviderService.Stub() {
		
		@Override
		public void registerListener(ILocationListener listener)
				throws RemoteException {
			Log.d(this.getClass().getName(), "Context provider service starting...");
			
			ContextListener cl = new ContextListener();
			LocationManager mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);			
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, cl);
		}
		
    };
	
}
