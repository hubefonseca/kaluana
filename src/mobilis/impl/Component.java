package mobilis.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mobilis.api.IReceptacle;
import mobilis.api.IService;
import mobilis.context.location.ISemanticLocationService;
import android.content.ContextWrapper;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public abstract class Component implements mobilis.api.IComponent,
										mobilis.api.adaptation.IAdaptable {
		
	/**
	 * This map stores the references between service names and 
	 * their interface and implementation, which will be used to return the implementation
	 * to the service consumer
	 */
	private HashMap<String, IService> services;
	
	/**
	 * This list stores the receptacles that can be connected to other
	 * components
	 */
	private HashMap<String, IReceptacle> receptacles;
	
	protected ContextWrapper contextWrapper;
	
	public Component() {
		services = new HashMap<String, IService>();
		receptacles = new HashMap<String, IReceptacle>();
	}

	@Override
	/**
	 * To bind this registered service to an implementation, use
	 * bindService() at loader level
	 */
	public void registerService(String serviceName, String interfaceName) throws RemoteException {
		IService service = new Service();
		service.setInterfaceName(interfaceName);
		service.setComponentName(getName());
		services.put(serviceName, service);
	}

	@Override
	public void registerReceptacle(String receptacleName, String interfaceName) throws RemoteException {
		Receptacle receptacle = new Receptacle();
		receptacle.setName(receptacleName);
		receptacle.setClassName(interfaceName);
		receptacles.put(receptacleName, receptacle);
	}
	
	@Override
	public IService getService(String serviceName) throws RemoteException {
		IService service = services.get(serviceName);
		return service;
	}

	@Override
	public IReceptacle getReceptacle(String receptacleName)
			throws RemoteException {		
		return receptacles.get(receptacleName);
	}

	@Override
	public void getServiceNames(List<String> serviceNames)
	throws RemoteException {
		for (String serviceName : services.keySet()) {
			serviceNames.add(serviceName);
		}
	}
	
	@Override
	public void getReceptacleNames(List<String> receptacleNames)
			throws RemoteException {
		for (String receptacleName : receptacles.keySet()) {
			receptacleNames.add(receptacleName);
		}
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
	
	public void bindService(String serviceName, IBinder binder) {
		try {
			IService service = services.get(serviceName);
			assert (service != null);
			
			service.setServiceImpl(binder);
			services.put(serviceName, service);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void bindReceptacle(String receptacleName, IService service) 
			throws RemoteException {
		try {
			if (service == null) {
				Log.d(this.getClass().getName(), "null service");
			}
			
			IReceptacle receptacle = getReceptacle(receptacleName);
			receptacle.connectToService(service);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public IService getBoundService(String receptacleName)
			throws RemoteException {
		IReceptacle receptacle = getReceptacle(receptacleName);
		return receptacle.getConnection();
	}
	
	@Override
	public String getServiceInterface(String serviceName)
			throws RemoteException {
		return services.get(serviceName).getInterfaceName();
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

