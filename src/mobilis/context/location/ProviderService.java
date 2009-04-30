package mobilis.context.location;

import java.util.List;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ProviderService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return mProviderImpl;
	}

	private final IProviderService.Stub mProviderImpl = new IProviderService.Stub() {
		
		private ContextListener cl = null;
		private LocationManager mLocationManager = null;
		private LocationProvider mLocationProvider = null;
		
		@Override
		public void registerListener(ILocationListener listener)
				throws RemoteException {
			Log.d(this.getClass().getName(), "Context provider service starting...");
			
			cl = new ContextListener();

//			mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);			
//			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, cl);
			
			mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
			Criteria criteria = new Criteria ();
			criteria.setAccuracy(Criteria.ACCURACY_FINE);
			List<String> providerIds = mLocationManager.getProviders(criteria, true);
			if (!providerIds.isEmpty()) {
				mLocationProvider = mLocationManager.getProvider(providerIds.get(0));

				if (mLocationProvider == null) {
					Log.d(this.getClass().getName(), "NULL PROVIDER");
				}
				
				Log.d(this.getClass().getName(), "Enabled: " + mLocationManager.isProviderEnabled(mLocationProvider.getName()));
				mLocationManager.requestLocationUpdates(mLocationProvider.getName(),
						0, 0, cl);
			}

		}
		
    };
	
}
