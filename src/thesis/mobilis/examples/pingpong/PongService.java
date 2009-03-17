package thesis.mobilis.examples.pingpong;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class PongService extends Service {

	private static int counter = 0;
	
	@Override
	public IBinder onBind(Intent intent) {
		return mPingImpl;
	}
	
	private final IPongService.Stub mPingImpl = new IPongService.Stub() {
		
    	@Override
		public int pong() throws RemoteException {
			return counter++;
		}

    };

}
