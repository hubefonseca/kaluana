package thesis.mobilis.api.examples.location;

import java.util.List;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import thesis.mobilis.api.Component;
import thesis.mobilis.api.Service;

public class LocationProviderB implements Component {

	@Override
	public Service getService(String serviceName) throws RemoteException {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "Location Provider B");
		return null;
	}

	@Override
	public void getServiceNames(List<String> serviceNames) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutdown() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startup() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
