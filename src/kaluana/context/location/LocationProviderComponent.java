package kaluana.context.location;

import kaluana.api.annotations.Component;
import kaluana.api.annotations.Service;
import android.os.RemoteException;
import android.util.Log;

@Component(category="kaluana.context.location")
public class LocationProviderComponent extends kaluana.impl.Component {

	@Service
	ISemanticLocationService semanticLocation;

	@Override
	public void start() throws RemoteException {
		Log.i(this.getClass().getName(), "Location Provider Component started!");
		
	}
	
	@Override
	public void stop() throws RemoteException {
		Log.i(this.getClass().getName(), "Location Provider Component stopped!");
	}

}
