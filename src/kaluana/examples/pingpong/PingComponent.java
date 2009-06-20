package kaluana.examples.pingpong;

import kaluana.api.annotations.Adaptable;
import kaluana.api.annotations.Component;
import kaluana.api.annotations.Dependencies;
import kaluana.api.annotations.Dependency;
import kaluana.api.annotations.Receptacle;
import kaluana.api.annotations.Service;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

@Component(category="kaluana.examples.ping")
@Adaptable
@Dependencies({@Dependency(name="pongDependency", componentCategory="kaluana.examples.ping")})
public class PingComponent extends kaluana.impl.Component {
	
	@Service
	IPingService ping;
	
	@Receptacle
	IPongService pong;
	
	@Override
	public void stop() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() throws RemoteException {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "Servico instanciado no ping component");
		
		IBinder service = getBoundService("pong");
		pong = IPongService.Stub.asInterface(service);
		
		int i = 0;
		while (i++ < 3) {
			int a = pong.pong();
			Log.d(this.getClass().getName(), "the result is " + a);
		}
	}
	
}
