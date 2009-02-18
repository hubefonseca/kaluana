package thesis.mobilis.api.examples.helloworld.services;

import thesis.mobilis.api.impl.object.Object;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class MobService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return mMobServiceBinderImpl2;
	}

    private final iMobService.Stub mMobServiceBinderImpl1 = new iMobService.Stub() {
		
    	@Override
		public int get() throws RemoteException {
			return 1;
		}

		@Override
		public Object getAnObject() throws RemoteException {
			// TODO Auto-generated method stub
			return new Object(2);
		}
    };
    
    private final iMobService.Stub mMobServiceBinderImpl2 = new iMobService.Stub() {
    	
		@Override
		public int get() throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getAnObject() throws RemoteException {
			// TODO Auto-generated method stub
			return new Object(3);
		}
		
		@Override
		public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
				throws RemoteException {
			
			Log.d(this.getClass().getName(), "It`s a code " + code + " transaction!");
			
			return true;
		}
    };


}
