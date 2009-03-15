package thesis.mobilis.impl;

import java.util.HashMap;
import java.util.List;

import thesis.mobilis.api.IBinderCallbackListener;
import thesis.mobilis.api.IReceptacle;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public abstract class Component implements thesis.mobilis.api.IComponent {
	
	/**
	 * This map stores the references between service names and 
	 * their interfaces, which will be used to return a implementation
	 * to the service consumer
	 */
	private HashMap<String, IBinder> services;
	
	private HashMap<String, IReceptacle> receptacles;

	/**
	 * Binder callback is used to notify the caller about
	 * the asynchronous binding operation end
	 */
	private IBinderCallbackListener bindListener;


	public Component(IBinderCallbackListener bindListener) {
		this.bindListener = bindListener;
		services = new HashMap<String, IBinder>();
		receptacles = new HashMap<String, IReceptacle>();
	}

	
	@Override
	public IBinder getService(String serviceName) throws RemoteException {
		IBinder service = services.get(serviceName);
		
		return service;
	}

	@Override
	public void getServiceNames(List<String> serviceNames)
	throws RemoteException {
		// TODO Auto-generated method stub

	}

	/**
	 * Supposed to be used by the component developer
	 */
	@Override
	public void registerService(String serviceName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Supposed to be used by the component developer
	 */
	@Override
	public void registerReceptacle(String serviceName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getReceptacleNames(List<String> receptacleNames)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}	
	

	@Override
	public IReceptacle getReceptacle(String receptacleName)
			throws RemoteException {
		
		Receptacle receptacle = new Receptacle();
		receptacle.setClassName(receptacleName);
		
		return receptacle;
	}
	
	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServiceConnection mServiceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className,
				IBinder service) {
			services.put("thesis.mobilis.examples.helloworld.services.iMobService", service);

			/**
			 * Notify the caller that process has ended
			 */
			try {
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
	
	@Override
	public abstract void shutdown() throws RemoteException;

	@Override
	public abstract void startup() throws RemoteException;

}
