package mobilis.examples.navigator;

import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;


public class NavigatorComponent extends mobilis.impl.Component {

	@Override
	public void connected(String receptacleName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(String receptacleName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerDependencies() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerReceptacles() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerServices() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() throws RemoteException {
		Log.d(this.getClass().getName(), "Navigator started!");
		
		Intent intent = new Intent(contextWrapper, NavigatorActivity.class);
		contextWrapper.startActivity(intent);
	}

	@Override
	public void stop() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
