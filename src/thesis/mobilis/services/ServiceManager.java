package thesis.mobilis.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class ServiceManager extends Service {

	/**
	 * The intent must receive information about current context, so it
	 * can decide which implementation of a service can be instantiated
	 * to best fit the moment requirements.
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mMobServiceBinderImpl2;
	}

    private final iMobService.Stub mMobServiceBinderImpl1 = new iMobService.Stub() {
		
    	@Override
		public int get() throws RemoteException {
			return 1;
		}
    };
    
    private final iMobService.Stub mMobServiceBinderImpl2 = new iMobService.Stub() {
		
    	@Override
		public int get() throws RemoteException {
			return 2;
		}
    };

    private final iAnotherService.Stub mAnotherServiceBinder = new iAnotherService.Stub() {

		@Override
		public int getId() throws RemoteException {
			return 11;
		}
       
    };
    
}
