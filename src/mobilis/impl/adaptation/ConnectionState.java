package mobilis.impl.adaptation;

import java.util.HashMap;

import mobilis.api.IService;

public class ConnectionState {

	/**
	 * Stores to which components the receptacles are connected
	 * <receptacle, receptacleState>
	 */
	public HashMap<String, ReceptacleState> receptacles;
	
	/**
	 * Stores which components are bound to each service
	 * <serviceName, serviceState>
	 */
	public HashMap<String, ServiceState> services;
	
	public ConnectionState() {
		receptacles = new HashMap<String, ReceptacleState>();
		services = new HashMap<String, ServiceState>();
	}
	
	public void addReceptacle(String receptacleName, IService service) {
		ReceptacleState recepatacleState = new ReceptacleState();
		recepatacleState.setService(service);
		receptacles.put(receptacleName, recepatacleState);
	}
	
	public void addService(String serviceName, String consumerComponentName, String receptacleName) {
		ServiceState serviceState = services.get(serviceName);
		if (serviceState == null) {
			serviceState = new ServiceState();
		}
		serviceState.addService(serviceName, receptacleName);
		services.put(serviceName, serviceState);
	}
	
}
