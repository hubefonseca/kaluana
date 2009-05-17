package mobilis.context.location;

import android.os.RemoteException;
import android.util.Log;

public class LocationProviderComponent extends mobilis.impl.Component {

	@Override
	public void registerDependencies() throws RemoteException {
		// No dependencies
		
	}

	@Override
	public void registerReceptacles() throws RemoteException {
		// No receptacles yet.
	}

	@Override
	public void registerServices() throws RemoteException {
		registerService("semanticLocation", "mobilis.context.location.ISemanticLocationService");
	}

	@Override
	public void start() throws RemoteException {
		Log.i(this.getClass().getName(), "Location Provider Component started!");
		
	}
	
	@Override
	public void stop() throws RemoteException {
		Log.i(this.getClass().getName(), "Location Provider Component stopped!");
	}
	
	@Override
	public String getCategory() throws RemoteException {
		return "mobilis.context.location";
	}

}
