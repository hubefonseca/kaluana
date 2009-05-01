package mobilis.examples.navigator.puc;

import mobilis.context.ExecutionScope;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;


public class NavigatorPUCComponent extends mobilis.impl.Component {

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
		Log.d(this.getClass().getName(), "Navigator PUC started!");
		
		Intent intent = new Intent(contextWrapper, NavigatorPUCActivity.class);
		contextWrapper.startActivity(intent);
	}

	@Override
	public void stop() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void registerScope() throws RemoteException {
		ExecutionScope executionScope = new ExecutionScope();
		executionScope.setLatitude(-43.232467);
		executionScope.setLongitude(-22.976253);
		executionScope.setRadius(1E4);
		registerExecutionScope(executionScope);
	}
	
}
