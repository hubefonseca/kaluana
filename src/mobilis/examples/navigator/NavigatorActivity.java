package mobilis.examples.navigator;

import java.util.List;

import mobilis.app.R;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class NavigatorActivity extends MapActivity implements LocationListener {

    private MapView mMapView;
    private MapController mc;
    
    private GeoPoint p = null;
    double latitude = 18.9599990845, longitude = 72.819999694;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.d(this.getClass().getName(), "Map view Navigator Activity");
        
        setContentView(R.layout.map);
        mMapView = (MapView) findViewById(R.id.mapView);
        mc = mMapView.getController();
        
        // Creating and initializing Map
		mMapView = (MapView) findViewById(R.id.mapView);
		p = new GeoPoint((int) (latitude * 1000000), (int) (longitude * 1000000));
		mMapView.setSatellite(true);
		mc = mMapView.getController();
		mc.setCenter(p);
		mc.setZoom(14);
		
		// Add a location mark
		MyLocationOverlay myLocationOverlay = new MyLocationOverlay(this, mMapView);
		myLocationOverlay.enableMyLocation();
		List<Overlay> list = mMapView.getOverlays();
		list.add(myLocationOverlay);

		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 50, this);
    }

    @Override
	public void onLocationChanged(Location location) {
    	if (location != null) {
			double lat = location.getLatitude();
			double lng = location.getLongitude();
			String currentLocation = "Lat: " + lat + " Lng: " + lng;
			Toast.makeText(getApplicationContext(), currentLocation, 5);
			p = new GeoPoint((int) lat * 1000000, (int) lng * 1000000);
			mc.animateTo(p);
		}
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
		
	}
	
	/* User can zoom in/out using keys provided on keypad */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_I) {
			mMapView.getController().setZoom(mMapView.getZoomLevel() + 1);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_O) {
			mMapView.getController().setZoom(mMapView.getZoomLevel() - 1);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_S) {
			mMapView.setSatellite(true);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_T) {
			mMapView.setTraffic(true);
			return true;
		}
		return false;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
