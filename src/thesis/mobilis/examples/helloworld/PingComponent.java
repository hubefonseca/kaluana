package thesis.mobilis.examples.helloworld;

import thesis.mobilis.api.IBinderCallbackListener;
import thesis.mobilis.examples.helloworld.services.iMobService;
import android.os.RemoteException;
import android.util.Log;

public class PingComponent extends thesis.mobilis.impl.Component {

	public PingComponent(IBinderCallbackListener bindListener) {
		super(bindListener);
		// TODO Auto-generated constructor stub
	}

	// receptacles :
	public iMobService mobService;	

	@Override
	public void shutdown() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startup() throws RemoteException {
		// TODO Auto-generated method stub
		
		int a = 0;
		try {
			a = mobService.get();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d(this.getClass().getName(), "the result is " + a);
	}

}
