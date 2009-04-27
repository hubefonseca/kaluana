package mobilis.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import mobilis.api.IReceptacle;
import mobilis.api.control.IReceptacleConnectionListener;

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
	protected ContextWrapper contextWrapper;
	
	/**
	 * Binder callback is used to notify the caller about
	 * the asynchronous binding operation end
	 */
	private IReceptacleConnectionListener listener;
	
	@Override
	public void setClassName(String className) throws RemoteException { 
		this.interfaceName = className;
	}
	
	public void setContextWrapper(ContextWrapper contextWrapper) {
		this.contextWrapper = contextWrapper;
	}
	
	public void setBindListener(IReceptacleConnectionListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void connectToService(IBinder service) 
			throws RemoteException {
		
		Log.d(this.getClass().getName(), "Connecting receptacle " + receptacleName + " to " + interfaceName);
		
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
				
				Class clazz = Class.forName(interfaceName);
				Class[] clazzes = clazz.getDeclaredClasses();
				/**
				 * It's expected that the first declared class inside the interface
				 * is its Stub.
				 */
				Method m = clazzes[0].getMethod("asInterface", new Class[]{android.os.IBinder.class});
				Object[] obj = new Object[]{service};
				serviceImpl = (IBinder)m.invoke(m, obj);
				
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
			
			/**
			 * Notify the caller that process has ended
			 */
			try {
				listener.connected(receptacleName);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.d(this.getClass().getName(), "Service disconnected");
		}
	};

}
