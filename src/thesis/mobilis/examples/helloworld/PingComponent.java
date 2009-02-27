package thesis.mobilis.examples.helloworld;

import java.util.List;

import thesis.mobilis.examples.helloworld.services.iMobService;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class PingComponent implements thesis.mobilis.api.Component {

	// receptacles :
	public iMobService mobService;	
	
	@Override
	public String getService(String serviceName) throws RemoteException {
		Log.d(this.getClass().getName(), "Hello world component getService(" + serviceName + ") called");
		return thesis.mobilis.examples.helloworld.services.iMobService.class.getName();
	}

	@Override
	public void getServiceNames(List<String> serviceNames)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startup() throws RemoteException {
		// TODO Auto-generated method stub
		
		int a = 0;
		try {
			a = mobService.get();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d(this.getClass().getName(), "the result is " + a);
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

}
