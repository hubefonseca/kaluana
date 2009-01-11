package thesis.mobilis;

import thesis.mobilis.services.MobObject;
import thesis.mobilis.services.iMobService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class Adaptation extends Activity {

	private iMobService mService;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bindService(new Intent(iMobService.class.getName()),
                mConnection, Context.BIND_AUTO_CREATE);
	}

	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className,
				IBinder service) {

			mService = iMobService.Stub.asInterface(service);
			try {
				int i = mService.get();
				Log.d(this.getClass().getName(), "Service answered: " + i);
				
				MobObject o = mService.getAnObject();
				Log.d(this.getClass().getName(), "Service now answered: " + o.getId());
				
			} catch (RemoteException e) {
				// In this case the service has crashed before we could even
				// do anything with it; we can count on soon being
				// disconnected (and then reconnected if it can be restarted)
				// so there is no need to do anything here.
			}

			// As part of the sample, tell the user what happened.

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
	};

}