package mobilis.examples.pingpong;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class PongComponent extends mobilis.impl.Component {

	IPingService pingService;

	@Override
	public void stop() throws RemoteException {
		Log.d(this.getClass().getName(), "shutdown");
	}

	@Override
	public void start() throws RemoteException {
		Log.d(this.getClass().getName(), "Pong component started");
		
		IBinder service = getBoundService("ping");
		pingService = IPingService.Stub.asInterface(service);
		
		int i = 0;
		while (i++ < 3) {
			int a = pingService.ping();
			Log.d(this.getClass().getName(), "the result is " + a);
		}
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerReceptacles() throws RemoteException {
		registerReceptacle("ping", "mobilis.examples.pingpong.IPingService");
	}

	@Override
	public void registerServices() throws RemoteException {
		registerService("pong", "mobilis.examples.pingpong.IPongService");
	}

	@Override
	public void registerDependencies() throws RemoteException {
		// no dependencies
	}

	@Override
	public String getCategory() throws RemoteException {
		return "mobilis.examples.pong";
	}

}
