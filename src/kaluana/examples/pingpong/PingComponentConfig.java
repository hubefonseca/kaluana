package kaluana.examples.pingpong;

import kaluana.api.control.IConfigService;
import kaluana.impl.control.ConfigService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PingComponentConfig extends Service {
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mConfig;
	}

	private final IConfigService.Stub mConfig = new ConfigService(new PingComponent(), this);
	
}
