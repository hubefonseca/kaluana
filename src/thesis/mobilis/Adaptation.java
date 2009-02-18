package thesis.mobilis;

import thesis.mobilis.api.Component;
import thesis.mobilis.api.examples.helloworld.services.iAdapter;
import thesis.mobilis.api.examples.helloworld.services.iMobService;
import thesis.mobilis.api.examples.location.LocationProviderA;
import thesis.mobilis.api.impl.loader.ComponentLoader;
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

//	private iMobService mService;
//	private iAdapter mAdapter;
	
	private Component component;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
//		Intent intent = new Intent(iAdapter.class.getName());
//		bindService(intent, mAdapterService, Context.BIND_AUTO_CREATE);
//		
//		Intent otherIntent = new Intent(iMobService.class.getName());
//		bindService(otherIntent, mMobService, Context.BIND_AUTO_CREATE);
		

			try {
				// Relacionar as classes com nome e versão do componente
				
				
				Log.d(this.getClass().getName(), "inicio");
				Component component = ComponentLoader.get("thesis.mobilis.api.examples.location.LocationProviderA");
				component.getService("a");
				
				Log.d(this.getClass().getName(), "meio");
				
				Intent intent = new Intent(LocationProviderA.class.getName());
				bindService(intent, mComponentService, Context.BIND_AUTO_CREATE);
				
				Log.d(this.getClass().getName(), "fim");
				Component component2 = ComponentLoader.get("thesis.mobilis.api.examples.location.LocationProviderB");
				component2.getService("a");
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
		
	}

//	private ServiceConnection mMobService = new ServiceConnection() {
//		public void onServiceConnected(ComponentName className,
//				IBinder service) {
//
//			mService = iMobService.Stub.asInterface(service);
//			
//			try {
//				mAdapter.register(service);
//				
//				Log.d(this.getClass().getName(), "The service is provided by " + className);
//				
//				int i = mService.get();
//				Log.d(this.getClass().getName(), "Service answered: " + i);
//				
//				MobObject o = mService.getAnObject();
//				Log.d(this.getClass().getName(), "Service now answered: " + o.getId());
//				
//			} catch (RemoteException e) {
//				// In this case the service has crashed before we could even
//				// do anything with it; we can count on soon being
//				// disconnected (and then reconnected if it can be restarted)
//				// so there is no need to do anything here.
//			}
//
//			// As part of the sample, tell the user what happened.
//
//		}
//
//		@Override
//		public void onServiceDisconnected(ComponentName name) {
//			// TODO Auto-generated method stub
//			
//		}
//	};
//	
//	private ServiceConnection mAdapterService = new ServiceConnection() {
//		public void onServiceConnected(ComponentName className,
//				IBinder service) {
//
//			mAdapter = iAdapter.Stub.asInterface(service);
////			try {
//				Log.d(this.getClass().getName(), "Service connected");
//				Log.d(this.getClass().getName(), "The service is provided by " + className);
//				
//				
////			} catch (RemoteException e) {
//				// In this case the service has crashed before we could even
//				// do anything with it; we can count on soon being
//				// disconnected (and then reconnected if it can be restarted)
//				// so there is no need to do anything here.
////			}
//
//			// As part of the sample, tell the user what happened.
//
//		}
//
//		@Override
//		public void onServiceDisconnected(ComponentName name) {
//			// TODO Auto-generated method stub
//			Log.d(this.getClass().getName(), "Service disconnected");
//		}
//	};

	
	private ServiceConnection mComponentService = new ServiceConnection() {
		public void onServiceConnected(ComponentName className,
				IBinder service) {

			component = Component.Stub.asInterface(service);
			
//			try {
				Log.d(this.getClass().getName(), "Service connected");
				Log.d(this.getClass().getName(), "The service is provided by " + className);
				
				
//			} catch (RemoteException e) {
				// In this case the service has crashed before we could even
				// do anything with it; we can count on soon being
				// disconnected (and then reconnected if it can be restarted)
				// so there is no need to do anything here.
//			}

			// As part of the sample, tell the user what happened.

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.d(this.getClass().getName(), "Service disconnected");
		}
	};
}