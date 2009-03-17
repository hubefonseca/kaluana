package thesis.mobilis.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import thesis.mobilis.api.IReceptacle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public abstract class Component implements thesis.mobilis.api.IComponent {
	
	/** 
	 * The component name
	 */
	private String name;
	
	/**
	 * This map stores the references between service names and 
	 * their interfaces, which will be used to return a implementation
	 * to the service consumer
	 */
	private HashMap<String, IBinder> services;
	
	/**
	 * This map stores the interfaces class names related to
	 * each service name
	 */
	private HashMap<String, String> servicesInterfaces;
	
	/**
	 * This list stores the receptacles that can be connected to other
	 * components
	 */
	private List<IReceptacle> receptacles;
	
	public Component() {
		services = new HashMap<String, IBinder>();
		receptacles = new ArrayList<IReceptacle>();
		servicesInterfaces = new HashMap<String, String>();
	}
	
	/**
	 * Supposed to be called by the component developer
	 */
	@Override
	public void registerService(String serviceName, String interfaceName) throws RemoteException {
		servicesInterfaces.put(serviceName, interfaceName);
	}

	/**
	 * Supposed to be used by the component developer
	 */
	@Override
	public void registerReceptacle(String receptacleName, String interfaceName) throws RemoteException {
		Receptacle receptacle = new Receptacle();
		receptacle.setClassName(interfaceName);
		receptacle.setName(receptacleName);
		receptacles.add(receptacle);
	}
	
	@Override
	public IBinder getService(String serviceName) throws RemoteException {
		return services.get(serviceName);
	}

	@Override
	public IReceptacle getReceptacle(String receptacleName)
			throws RemoteException {
		Iterator<IReceptacle> iterator = receptacles.iterator();
		IReceptacle receptacle;
		while (iterator.hasNext()) {
			receptacle = iterator.next();
			Log.d(this.getClass().getName(), receptacle.getName());
			if (receptacle.getName().equals(receptacleName))
				return receptacle;
		}
		return null;
	}

	@Override
	public void getServiceNames(List<String> serviceNames)
	throws RemoteException {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void getReceptacleNames(List<String> receptacleNames)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public abstract void start() throws RemoteException;

	@Override
	public abstract void stop() throws RemoteException;
	
}

