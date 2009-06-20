package kaluana.examples.pingpong;

import kaluana.api.annotations.Adaptable;
import kaluana.api.annotations.Component;
import kaluana.api.annotations.Receptacle;
import kaluana.api.annotations.Service;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

@Component(category="kaluana.examples.pong")
@Adaptable
public class PongComponent extends kaluana.impl.Component {

	@Service
	IPongService pong;

	@Receptacle
	IPingService ping;
	
	@Override
	public void stop() throws RemoteException {
		Log.d(this.getClass().getName(), "shutdown");
	}

	@Override
	public void start() throws RemoteException {
		Log.d(this.getClass().getName(), "Pong component started");
		
		IBinder service = getBoundService("ping");
		ping = IPingService.Stub.asInterface(service);
		
		int i = 0;
		while (i++ < 3) {
			int a = ping.ping();
			Log.d(this.getClass().getName(), "the result is " + a);
		}
	}

}
