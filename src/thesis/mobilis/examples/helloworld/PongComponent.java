package thesis.mobilis.examples.helloworld;

import java.util.List;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class PongComponent implements thesis.mobilis.api.Component {

	@Override
	public String getService(String serviceName) throws RemoteException {
		Log.d(this.getClass().getName(), "Hello world component getService(" + serviceName + ") called");
		
		return thesis.mobilis.examples.helloworld.services.iMobService.class.getName();
	}

	@Override
	public void getServiceNames(List<String> serviceNames)
			throws RemoteException {
		// TODO Auto-generated method stub
		
		//serviceNames = new ArrayList<String>();
		
		serviceNames.clear();
		serviceNames.add("teste 1");
		serviceNames.add("teste 2");
		serviceNames.add("teste 3");
		serviceNames.add("teste 4");
		
	}

	@Override
	public void shutdown() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startup() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}	

}
