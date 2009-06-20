package kaluana.context.location;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class SemanticLocationService extends Service {
	
	@Override
	public IBinder onBind(Intent intent) {
		return mServiceImpl;
	}
	
	private final ISemanticLocationService.Stub mServiceImpl = new ISemanticLocationService.Stub() {

		@Override
		public String getSemanticLocation() throws RemoteException {
			return "You are here!";
		}
		
    };

}