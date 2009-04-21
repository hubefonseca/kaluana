package mobilis.examples.helloworld.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * 
 * @author Hubert
 *
 */
public class AdapterService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return mAdapter;
	}

	private final iAdapter.Stub mAdapter = new iAdapter.Stub() {

		@Override
		public void register(IBinder service) throws RemoteException {
			Log.d(this.getClass().getName(), "Service " + service.getClass().getName() + " registered at mAdapter");
			
			service.transact(1, null, null, FLAG_ONEWAY);
		}

		@Override
		public void unregister(IBinder service) throws RemoteException {
			Log.d(this.getClass().getName(), "Service " + service.getInterfaceDescriptor() + " unregistered at mAdapter");
		}
		
	};
}
