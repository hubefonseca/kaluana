package mobilis.impl;

import mobilis.api.IReceptacle;
import mobilis.api.IService;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class Receptacle implements IReceptacle {

	private String receptacleName;
	private String interfaceName;	

	private IService service;
	
	@Override
	public void setClassName(String className) throws RemoteException { 
		this.interfaceName = className;
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
	public IService getConnection() throws RemoteException {
		
//		Class clazz = Class.forName(interfaceName);
//		Class[] clazzes = clazz.getDeclaredClasses();
//		
//		/**
//		 * It's expected that the first declared class inside the interface
//		 * is its Stub.
//		 */
//		Method m = clazzes[0].getMethod("asInterface", new Class[]{android.os.IBinder.class});
//		Object[] obj = new Object[]{serviceImpl};
//		
//		Object o = m.invoke(m, obj);
//		return (IBinder)o;
		
		return service;
	}

	@Override
	/**
	 * Binds serviceImpl to service implementation instance.
	 */
	public void connectToService(IService service) 
			throws RemoteException {
		Log.d(this.getClass().getName(), "Connecting receptacle " + interfaceName + " to " + service.getName());
		this.service = service;
	}
	
}
