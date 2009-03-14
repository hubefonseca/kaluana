package thesis.mobilis.api.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import thesis.mobilis.api.IReceptacle;
import thesis.mobilis.examples.helloworld.services.iMobService;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class Receptacle implements IReceptacle {

	private String className;
	
	@Override
	public void setClassName(String className) throws RemoteException { 
		this.className = className;
	}
	
	@Override
	public void connectToService(IBinder service) 
			throws RemoteException {
		// TODO Auto-generated method stub
		try {

			Log.d(this.getClass().getName(), "bound?");

			Class clazz = Class.forName(className);
			Class[] clazzes = clazz.getDeclaredClasses();

			/**
			 * It's expected that the first declared class inside the interface
			 * is its Stub.
			 */
			Method m = clazzes[0].getMethod("asInterface", new Class[]{android.os.IBinder.class});

			Object[] obj = new Object[]{service};

			IBinder serviceImpl = null;
			serviceImpl = (IBinder)m.invoke(m, obj);

			Log.d(this.getClass().getName(), "final");

			Log.d(this.getClass().getName(), "COMP " + ((iMobService)serviceImpl).get());


			Log.d(this.getClass().getName(), "testando 1 " + ((iMobService)service).get());

			Log.d(this.getClass().getName(), "fazendo experiencias " + ((iMobService)service).get());


			//		try {
			//
			////			Log.d(this.getClass().getName(), "ADAPT " + ((iMobService)pongComponent.s).get());
			//
			//		} catch (RemoteException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		}

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
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

}
