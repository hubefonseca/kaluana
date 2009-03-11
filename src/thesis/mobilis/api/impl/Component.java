package thesis.mobilis.api.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import thesis.mobilis.api.IBinderCallbackListener;
import thesis.mobilis.examples.helloworld.services.iMobService;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public abstract class Component implements thesis.mobilis.api.IComponent {

	private HashMap<String, thesis.mobilis.api.impl.Service> services;
	private IBinderCallbackListener bindListener;
	
	// just testing
	public iMobService mobService;
	
	
	public Component(IBinderCallbackListener bindListener) {
		this.bindListener = bindListener;
		services = new HashMap<String, thesis.mobilis.api.impl.Service>();
	}
	
	@Override
	public String getService(String serviceName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getServiceNames(List<String> serviceNames)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerService(String serviceName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public abstract void shutdown() throws RemoteException;

	@Override
	public abstract void startup() throws RemoteException;

	public ServiceConnection mServiceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className,
				IBinder service) {

			try {
				Class clazz = Class.forName("thesis.mobilis.examples.helloworld.services.iMobService");
				Class[] clazzes = clazz.getDeclaredClasses();
				Method m = clazzes[0].getMethod("asInterface", new Class[]{android.os.IBinder.class});
				
				Object[] obj = new Object[]{service};

				mobService = (iMobService)m.invoke(m, obj);
				
				Log.d(this.getClass().getName(), "final");
				
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
			
			try {
				
				Log.d(this.getClass().getName(), "COMP " + mobService.get());
				
				bindListener.bound();
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			try {
				Log.d(this.getClass().getName(), "Service connected");
				Log.d(this.getClass().getName(), "The service is provided by " + className);				
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
