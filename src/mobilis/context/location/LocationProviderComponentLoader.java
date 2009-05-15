package mobilis.context.location;

import mobilis.api.control.ILocalLoader;
import mobilis.impl.control.LocalLoader;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LocationProviderComponentLoader extends Service {
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mComponentManager;
	}

	private final ILocalLoader.Stub mComponentManager = new LocalLoader(new LocationProviderComponent(), this);
	
}
