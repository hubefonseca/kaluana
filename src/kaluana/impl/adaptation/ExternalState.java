package kaluana.impl.adaptation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kaluana.api.ReceptacleInfo;
import kaluana.api.ServiceInfo;


public class ExternalState {

	/**
	 * Stores to which components the receptacles are connected 
	 */
	public List<ReceptacleInfo> receptacles;
	
	/**
	 * Stores which components are bound to each service
	 * <serviceName, serviceState>
	 */
	public HashMap<ServiceInfo, List<ReceptacleInfo>> services;
	
	public ExternalState() {
		receptacles = new ArrayList<ReceptacleInfo>();
		services = new HashMap<ServiceInfo, List<ReceptacleInfo>>();
	}
	
	public void addReceptacle(ReceptacleInfo receptacleInfo) {
		receptacles.add(receptacleInfo);
	}
	
	public void addService(ServiceInfo serviceInfo, ReceptacleInfo receptacleInfo) {
		List<ReceptacleInfo> receptacles = services.get(serviceInfo);
		if (receptacles == null) {
			receptacles = new ArrayList<ReceptacleInfo>();
		}
		receptacles.add(receptacleInfo);
		services.put(serviceInfo, receptacles);
	}

	public List<ReceptacleInfo> getReceptacles() {
		return receptacles;
	}

	public HashMap<ServiceInfo, List<ReceptacleInfo>> getServices() {
		return services;
	}
	
}
