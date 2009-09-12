package kaluana.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import kaluana.api.ReceptacleInfo;
import kaluana.api.ServiceInfo;
import kaluana.impl.adaptation.InternalState;
import android.content.ContextWrapper;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public abstract class Component implements kaluana.api.IComponent {

	/**
	 * This map stores the references between service names and their
	 * implementation, which will be used to return the implementation to
	 * the service consumer
	 */
	private HashMap<ServiceInfo, IBinder> services;

	/**
	 * This list stores the receptacles and, after connected,
	 * their implementation at other components
	 */
	private HashMap<ReceptacleInfo, IBinder> receptacles;

	/**
	 * Context wrapper is used to allow component to bind to remote
	 * services
	 */
	protected ContextWrapper contextWrapper;

	public Component() {
		services = new HashMap<ServiceInfo, IBinder>();
		receptacles = new HashMap<ReceptacleInfo, IBinder>();
	}

	@Override
	public String getSimpleName() throws RemoteException {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public String getFullName() throws RemoteException {
		return getCategory() + "." + getSimpleName();
	}
	
	@Override
	public String getCategory() throws RemoteException {
		Annotation annotation = this.getClass().getAnnotation(kaluana.api.annotations.Component.class);
		if (annotation == null) {
			Log.e(this.getClass().getName(), "Component should be annotated with @Component annotation");
		}	
		return ((kaluana.api.annotations.Component)annotation).category();
	}
	
	public void registerService(String serviceName, String interfaceName)
			throws RemoteException {
		ServiceInfo serviceInfo = new ServiceInfo(serviceName, interfaceName,
				getFullName());
		services.put(serviceInfo, null);
	}

	public void registerReceptacle(String receptacleName, String interfaceName)
			throws RemoteException {
		ReceptacleInfo receptacleInfo = new ReceptacleInfo(receptacleName,
				interfaceName, getFullName());
		receptacles.put(receptacleInfo, null);
	}

	@Override
	public IBinder getService(String serviceName) throws RemoteException {
		for (ServiceInfo serviceInfo : services.keySet()) {
			if (serviceInfo.getName().equals(serviceName)) {
				return services.get(serviceInfo);
			}
		}
		return null;
	}

	@Override
	public void getServiceNames(List<String> serviceNames)
			throws RemoteException {
		for (ServiceInfo serviceInfo : services.keySet()) {
			serviceNames.add(serviceInfo.getName());
		}
	}

	@Override
	public void getReceptacleNames(List<String> receptacleNames)
			throws RemoteException {
		for (ReceptacleInfo receptacleInfo : receptacles.keySet()) {
			receptacleNames.add(receptacleInfo.getName());
		}
	}

	public ServiceInfo getServiceInfo(String serviceName)
			throws RemoteException {
		for (ServiceInfo serviceInfo : services.keySet()) {
			return serviceInfo;
		}
		return null;
	};

	@Override
	public ReceptacleInfo getReceptacleInfo(String receptacleName)
			throws RemoteException {
		for (ReceptacleInfo receptacleInfo : receptacles.keySet()) {
			return receptacleInfo;
		}
		return null;
	}

	public void setContextWrapper(ContextWrapper contextWrapper) {
		this.contextWrapper = contextWrapper;
	}

	public ContextWrapper getContextWrapper() {
		return contextWrapper;
	}

	public void bindService(String serviceName, IBinder binder) {
		for (ServiceInfo serviceInfo : services.keySet()) {
			if (serviceInfo.getName().equals(serviceName)) {
				services.put(serviceInfo, binder);
				return;
			}
		}
	}

	public void bindReceptacle(ReceptacleInfo receptacleInfo, IBinder service,
			ServiceInfo serviceInfo) throws RemoteException {
		receptacleInfo.setServiceInfo(serviceInfo);
		receptacles.put(receptacleInfo, service);
		
		String receptacleName = receptacleInfo.getName();
		Class<?> clazz = null;
		Class<?>[] clazzes = null;
		Method asInterface = null;
		Method setter = null;
		String interfaceName = receptacleInfo.getInterfaceName();
		
		try {
			clazz = Class.forName(interfaceName);
			clazzes = clazz.getDeclaredClasses();
			/**
			* It's expected that the first declared class inside the interface
			* is its Stub.
			*/
			asInterface = clazzes[0].getMethod("asInterface", new Class[]{android.os.IBinder.class});
			
		} catch (ClassNotFoundException e) {
			Log.e(this.getClass().getName(), "Client doesn't know interface " + interfaceName);
		} catch (SecurityException e) {
			Log.e(this.getClass().getName(), "Service does not implement " + interfaceName);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			Log.e(this.getClass().getName(), "Implementation for " + interfaceName + " is not a service or Stub isn't its first declared field");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String methodName = "set" + receptacleName.substring(0, 1).toUpperCase();
		if (receptacleName.length() > 1) {
			methodName += receptacleName.substring(1);
		}
		
		try {
			setter = this.getClass().getMethod(methodName, new Class[]{clazz});
			Object boundService = asInterface.invoke(asInterface, new Object[]{service});
			setter.invoke(this, new Object[]{boundService});
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			Log.e(this.getClass().getName(), "Is setter method present for all receptacles?");
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
		}
	}

	@Override
	public IBinder asBinder() {
		// No usage for this.
		return null;
	}

	@Override
	public void start() throws RemoteException {
	};

	@Override
	public void stop() throws RemoteException {
	};

	public InternalState getInternalState() {
		return null;
	};

	public void setInternalState(InternalState state) {
	};
	
	@Override
	/**
	 * This method will be called at the end of component 
	 * activation process. Override it to set specific behavior
	 */
	public void onActive() throws RemoteException {
		
	}
	
	@Override
	/**
	 * This method will be called at the end of component 
	 * inactivation process. Override it to set specific behavior
	 */
	public void onInactive() throws RemoteException {
		
	}

}
