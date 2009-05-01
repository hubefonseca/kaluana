package mobilis.context;

import mobilis.api.adaptation.IAdaptationManager;
import mobilis.context.location.ContextListener;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
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
		
		private IAdaptationManager adaptationManager = null;
		private IContextListener cl = null;
		private LocationManager mLocationManager = null;
//		private LocationProvider mLocationProvider = null;
		
		@Override
		public void start()
				throws RemoteException {
			Log.d(this.getClass().getName(), "Context provider service starting...");
			
			cl = new ContextListener();
			cl.registerAdaptationManager(adaptationManager);
			
			mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);			
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener)cl);
		}

		@Override
		public void registerAdaptationManager(
				IAdaptationManager adaptationManager) throws RemoteException {
			this.adaptationManager = adaptationManager;
		}
		
    };
	
}
