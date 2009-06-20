package kaluana.examples.pingpong;

import kaluana.api.control.ILocalLoader;
import kaluana.impl.control.LocalLoader;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PingComponentLoader extends Service {
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mComponentManager;
	}

	private final ILocalLoader.Stub mComponentManager = new LocalLoader(new PingComponent(), this);
	
}
