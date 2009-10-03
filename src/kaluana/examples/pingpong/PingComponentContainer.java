package kaluana.examples.pingpong;

import kaluana.api.IContainer;
import kaluana.impl.Container;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PingComponentContainer extends Service {
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mConfig;
	}

	private final IContainer.Stub mConfig = new Container(new PingComponent(), this);
	
}
