package thesis.mobilis.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import thesis.mobilis.api.IBinderCallbackListener;
import thesis.mobilis.api.IReceptacle;
import thesis.mobilis.examples.pingpong.IPingService;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class Receptacle implements IReceptacle {

	private String receptacleName;
	private String interfaceName;
	private IBinder serviceImpl;
	
	/**
	 * Used to bind services
	 */
	private ContextWrapper contextWrapper;
	
	/**
	 * Binder callback is used to notify the caller about
	 * the asynchronous binding operation end
	 */
	private IBinderCallbackListener listener;
	
	@Override
	public void setClassName(String className) throws RemoteException { 
		this.interfaceName = className;
	}
	
	public void setContextWrapper(ContextWrapper contextWrapper) {
		this.contextWrapper = contextWrapper;
	}
	
	public void setBinderCallbackListener(IBinderCallbackListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void connectToService(IBinder service) 
			throws RemoteException {
		
		Log.d(this.getClass().getName(), "connect to " + interfaceName);
		
		Intent intent = new Intent(interfaceName);
		contextWrapper.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
		
	}

	@Override
	public void setName(String name) {
		this.receptacleName = name;
	}
	
	@Override
	public String getName() {
		return receptacleName;
	}
	
	@Override
	public IBinder asBinder() {
		return null;
	}

	@Override
	public IBinder getConnection() throws RemoteException {
		return serviceImpl;
	}

	public ServiceConnection mServiceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className,
				IBinder service) {
			
			try {
				if (service == null) {
					Log.d(this.getClass().getName(), "null service!!!");
				}
				
				Class clazz = Class.forName(interfaceName);
				Class[] clazzes = clazz.getDeclaredClasses();

				/**
				 * It's expected that the first declared class inside the interface
				 * is its Stub.
				 */
				Method m = clazzes[0].getMethod("asInterface", new Class[]{android.os.IBinder.class});

				Object[] obj = new Object[]{service};

				serviceImpl = (IBinder)m.invoke(m, obj);
				
				Log.d(this.getClass().getName(), receptacleName + " invoked!");
				
				if (receptacleName.equals("ping")) {
					Log.d(this.getClass().getName(), "Ping");
					try {
						Log.d(this.getClass().getName(), ((IPingService)serviceImpl).ping() + "");
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Log.d(this.getClass().getName(), "Service connected");
			Log.d(this.getClass().getName(), "Service provided by " + className);	
			
			
			/**
			 * Notify the caller that process has ended
			 */
			try {
				listener.bound(receptacleName);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			try {
						
			//			} catch (RemoteException e) {
			// In this case the service has crashed before we could even
			// do anything with it; we can count on soon being
			// disconnected (and then reconnected if it can be restarted)
			// so there is no need to do anything here.
			//			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.d(this.getClass().getName(), "Service disconnected");
		}
	};
}
