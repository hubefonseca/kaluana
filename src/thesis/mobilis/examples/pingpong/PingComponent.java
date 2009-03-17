package thesis.mobilis.examples.pingpong;

import thesis.mobilis.api.IReceptacle;
import thesis.mobilis.examples.helloworld.services.iMobService;
import android.content.ContextWrapper;
import android.os.RemoteException;
import android.util.Log;

public class PingComponent extends thesis.mobilis.impl.Component {

	IPongService pongService;
	
	public PingComponent() {
		try {
			setName("PingComponent");
			registerService("ping", "thesis.mobilis.examples.pingpong.IPingService");
			registerReceptacle("pong", "thesis.mobilis.examples.pingpong.IPongService");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

}
