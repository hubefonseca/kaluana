package thesis.mobilis.examples.pingpong;

import thesis.mobilis.api.IReceptacle;
import thesis.mobilis.examples.helloworld.services.iMobService;
import android.os.RemoteException;
import android.util.Log;

public class PingComponent extends thesis.mobilis.impl.Component {

	IPongService pongService;

	// receptacles :
	public iMobService mobService;	

	@Override
	public void stop() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() throws RemoteException {
		
		IReceptacle r = this.getReceptacle("pong");
		
		pongService = (IPongService)r.getConnection();
		
		int a = pongService.pong();
		
		Log.d(this.getClass().getName(), "the result is " + a);
	}

	@Override
	public void registerReceptacles() throws RemoteException {
		registerReceptacle("pong", "thesis.mobilis.examples.pingpong.IPongService");
	}

	@Override
	public void registerServices() throws RemoteException {
		registerService("ping", "thesis.mobilis.examples.pingpong.IPingService");
	}

	@Override
	public void registerDependencies() throws RemoteException {
		// no dependencies
	}
	
}
