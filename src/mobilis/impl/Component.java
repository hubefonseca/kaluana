package mobilis.impl;

import java.util.HashMap;
import java.util.List;

import mobilis.api.ReceptacleInfo;
import mobilis.api.ServiceInfo;
import android.content.ContextWrapper;
import android.os.IBinder;
import android.os.RemoteException;

public abstract class Component implements mobilis.api.IComponent,
										mobilis.api.adaptation.IAdaptable {
		
	/**
	 * This map stores the references between service names and 
	 * their interface and implementation, which will be used to return the implementation
	 * to the service consumer
	 */
	private HashMap<ServiceInfo, IBinder> services;
	
	/**
	 * This list stores the receptacles that can be connected to other
	 * components
	 */
	private HashMap<ReceptacleInfo, IBinder> receptacles;
	
	protected ContextWrapper contextWrapper;
	
	public Component() {
		services = new HashMap<ServiceInfo, IBinder>();
		receptacles = new HashMap<ReceptacleInfo, IBinder>();
	}

	@Override
	/**
	 * To bind this registered service to an implementation, use
	 * bindService() at loader level
	 */
	public void registerService(String serviceName, String interfaceName) throws RemoteException {
		ServiceInfo serviceInfo = new ServiceInfo(serviceName, interfaceName, getName());
		services.put(serviceInfo, null);
	}

	@Override
	public void registerReceptacle(String receptacleName, String interfaceName) throws RemoteException {
		ReceptacleInfo receptacleInfo = new ReceptacleInfo(receptacleName, interfaceName, getName());
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
	
	@Override
	public String getName() {
		return this.getClass().getName();
	}
	
	@Override
	public void registerDependency(String componentName) throws RemoteException {
		// TODO Auto-generated method stub
		
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
	
	public void bindReceptacle(ReceptacleInfo receptacleInfo, IBinder service, ServiceInfo serviceInfo) 
			throws RemoteException {
		receptacleInfo.setServiceInfo(serviceInfo);
		receptacles.put(receptacleInfo, service);
	}
	
	@Override
	public IBinder getBoundService(String receptacleName)
			throws RemoteException {
		for (ReceptacleInfo receptacleInfo : receptacles.keySet()) {
			if (receptacleInfo.getName().equals(receptacleName)) {
				return receptacles.get(receptacleInfo);
			}
		}
		return null;
	}
	
	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Methods to be implemented by component developer
	 */
	@Override
	public abstract void registerReceptacles() throws RemoteException;

	@Override
	public abstract void registerServices() throws RemoteException;
	
	@Override
	public void registerDependencies() throws RemoteException {};
	
	@Override
	public void getDependencies() throws RemoteException {};
	
	@Override
	public void start() throws RemoteException {};

	@Override
	public void stop() throws RemoteException {};
	
}

