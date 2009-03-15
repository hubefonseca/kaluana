package thesis.mobilis.examples.helloworld;

import thesis.mobilis.api.IBinderCallbackListener;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class PongComponent extends thesis.mobilis.impl.Component {

	public PongComponent(IBinderCallbackListener bindListener) {
		super(bindListener);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void shutdown() throws RemoteException {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "shutdown");
	}

	@Override
	public void startup() throws RemoteException {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "startup");
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

}
