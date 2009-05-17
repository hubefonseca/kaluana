package mobilis.examples.pingpong;

import mobilis.api.IReceptacle;
import mobilis.api.IService;
import mobilis.context.location.ISemanticLocationService;
import android.os.RemoteException;
import android.util.Log;

public class PingComponent extends mobilis.impl.Component {

	IPongService pongService;

	@Override
	public void stop() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() throws RemoteException {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "Servico instanciado no ping component");
		
		IReceptacle r = this.getReceptacle("pong");
		
		IService service = r.getConnection();
		pongService = IPongService.Stub.asInterface(service.getServiceImpl());
		
		int i = 0;
		while (i++ < 3) {
			int a = pongService.pong();
			Log.d(this.getClass().getName(), "the result is " + a);
		}
		
	}

	@Override
	public void registerReceptacles() throws RemoteException {
		registerReceptacle("pong", "mobilis.examples.pingpong.IPongService");
	}

	@Override
	public void registerServices() throws RemoteException {
		registerService("ping", "mobilis.examples.pingpong.IPingService");
	}

	@Override
	public void registerDependencies() throws RemoteException {
		// no dependencies
	}
	
	@Override
	public String getCategory() throws RemoteException {
		return "mobilis.examples.ping";
	}

}
