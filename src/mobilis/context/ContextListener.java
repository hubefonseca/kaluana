package mobilis.context;

import mobilis.api.adaptation.IAdaptationManager;
import mobilis.context.IContextListener;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ContextListener implements IContextListener, LocationListener {

	private IAdaptationManager adaptationManager;
	
	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.d(this.getClass().getName(), "Location changed");
		try {
			Context context = new Context();
			context.setLatitude(location.getLatitude());
			context.setLongitude(location.getLongitude());
			adaptationManager.onContextChange(context);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerAdaptationManager(IAdaptationManager adaptationManager)
			throws RemoteException {
		this.adaptationManager = adaptationManager;
	}

}
