package kaluana.context.location;

import kaluana.api.control.ILocalLoader;
import kaluana.impl.control.LocalLoader;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LocationProviderComponentLoader extends Service {
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mLocalLoader;
	}

	private final ILocalLoader.Stub mLocalLoader = new LocalLoader(new LocationProviderComponent(), this);
	
}
