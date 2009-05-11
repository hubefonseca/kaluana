package mobilis.examples.pingpong;

import mobilis.api.IReceptacle;
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
	public void connected(String receptacleName) throws RemoteException {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "Servico instanciado no pong component");
		
		IReceptacle r = this.getReceptacle("ping");
		
		pingService = (IPingService)r.getConnection();
		
		int a = pingService.ping();
		
		Log.d(this.getClass().getName(), "the result is " + a);

	}

	@Override
	public void disconnected(String receptacleName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCategory() throws RemoteException {
		return "mobilis.examples.pong";
	}

}
