package kaluana.examples.pingpong;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class PingService extends Service {

	private static int counter = 1000;
	
	@Override
	public IBinder onBind(Intent intent) {
		return mPingImpl;
	}
	 
	private final IPingService.Stub mPingImpl = new IPingService.Stub() {
		
    	@Override
		public int ping() throws RemoteException {
			return counter--;
		}

    };

}
