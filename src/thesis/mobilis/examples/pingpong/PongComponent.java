package thesis.mobilis.examples.pingpong;

import thesis.mobilis.api.IReceptacle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class PongComponent extends thesis.mobilis.impl.Component {

	IPingService pingService;
	
	public PongComponent() {
		try {
			setName("PongComponent");
			registerService("pong", "thesis.mobilis.examples.pingpong.IPongService");
			registerReceptacle("ping", "thesis.mobilis.examples.pingpong.IPingService");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void stop() throws RemoteException {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "shutdown");
	}

	@Override
	public void start() throws RemoteException {
		
		Log.d(this.getClass().getName(), "Pong component started");
		
		IReceptacle r = this.getReceptacle("ping");
		
		pingService = (IPingService)r.getConnection();
		
		int a = pingService.ping();
		
		Log.d(this.getClass().getName(), "the result is " + a);

		a = pingService.ping();

		Log.d(this.getClass().getName(), "the result is " + a);

		a = pingService.ping();

		Log.d(this.getClass().getName(), "the result is " + a);

	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

}
