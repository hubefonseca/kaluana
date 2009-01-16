package thesis.mobilis.services.mobService;

import thesis.mobilis.objects.MobObject;
import thesis.mobilis.services.iMobService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

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
		public MobObject getAnObject() throws RemoteException {
			// TODO Auto-generated method stub
			return new MobObject(2);
		}
    };
    
    private final iMobService.Stub mMobServiceBinderImpl2 = new iMobService.Stub() {
    	
		@Override
		public int get() throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public MobObject getAnObject() throws RemoteException {
			// TODO Auto-generated method stub
			return new MobObject(3);
		}
    };


}
