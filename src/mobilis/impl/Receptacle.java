package mobilis.impl;

import java.lang.reflect.Method;

import mobilis.api.IReceptacle;
import mobilis.examples.pingpong.IPingService;
import mobilis.examples.pingpong.IPongService;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class Receptacle implements IReceptacle {

	private String receptacleName;
	private String interfaceName;
	private IBinder serviceImpl;
	
	@Override
	public void setClassName(String className) throws RemoteException { 
		this.interfaceName = className;
	}
	
	@Override
	public void connectToService(IBinder service) 
			throws RemoteException {
		try {
			Log.d(this.getClass().getName(), "Connecting receptacle " + interfaceName + " to service.");
			
			Class clazz = Class.forName(interfaceName);
			Class[] clazzes = clazz.getDeclaredClasses();
			/**
			 * It's expected that the first declared class inside the interface
			 * is its Stub.
			 */
			Method m = clazzes[0].getMethod("asInterface", new Class[]{android.os.IBinder.class});
			Object[] obj = new Object[]{service};
			serviceImpl = (IBinder)m.invoke(m, obj);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
