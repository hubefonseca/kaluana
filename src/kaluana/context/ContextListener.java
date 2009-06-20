package kaluana.context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import kaluana.api.adaptation.IAdaptationManager;
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
		String loc = null;
		try {
			kaluana.context.Context context = new kaluana.context.Context();
			try {
				URL url = new URL("http://10.0.2.2:8080/location/provider");
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setDoOutput(true);

				OutputStreamWriter out = new OutputStreamWriter(
						conn.getOutputStream());
				out.write("latitude=" + location.getLatitude() + "&longitude=" + location.getLongitude());
				out.close();

				BufferedReader in = new BufferedReader(
						new InputStreamReader(
								conn.getInputStream()));

				StringBuffer response = new StringBuffer();
				int c = in.read();
				while (c > -1) {
					if (c != 10 && c != 13) {
						response.append((char)c);
					}
					c = in.read();
				}
				in.close();

				loc = response.toString();
				
				if (loc.equals("")) {
					loc = "loc";
					Log.e(this.getClass().getName(), "Location not found! Using default value: " + loc);
				}
				Log.d(this.getClass().getName(), "Location: " + loc);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			context.setLatitude(location.getLatitude());
			context.setLongitude(location.getLongitude());
			context.setLocation(loc);
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
