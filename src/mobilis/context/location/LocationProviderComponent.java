package mobilis.context.location;

import android.os.RemoteException;

public class LocationProviderComponent extends mobilis.impl.Component {

	IProviderService providerService;
	
	public LocationProviderComponent() {
		
	}

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
		// No receptacles yet.
	}

	@Override
	public void registerServices() throws RemoteException {
		registerService("provider", "mobilis.impl.context.location.IProviderService");
	}

	@Override
	public void start() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
}
