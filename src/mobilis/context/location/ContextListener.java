package mobilis.context.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

public class ContextListener extends Thread implements LocationListener {

	public ContextListener() {
		Looper.prepare();
		Looper.loop();
		Log.d(this.getClass().getName(), "Listener constructor");
	}
	
	@Override
	public void onLocationChanged(Location location) {
		Log.d(this.getClass().getName(), "Location change updated");
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "Location provider disabled");
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "Location provider enabled");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "Status changed: " + status);
	}

	public void test() {
		Log.d(this.getClass().getName(), "Test!!!");
	}
}
