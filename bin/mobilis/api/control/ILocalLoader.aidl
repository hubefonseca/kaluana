package mobilis.api.control;

import mobilis.api.IComponent;
import mobilis.api.ServiceInfo;
import mobilis.api.ReceptacleInfo;

interface ILocalLoader {

	/*
	 * Called by the middleware
	 */
	String getName();
	
	String getCategory();
	
	void bindService(String serviceName, IBinder binder);
	
	void start();
	
	void stop();
	
	/*
	 * Must be implemented by component developer
	 */
	void registerServices();
	
	void registerReceptacles();

	/*
	 * Should be called by component user
	 */
	void getServiceNames(out List<String> serviceNames);
	
	void getReceptacleNames(out List<String> receptacleNames);
	
	IBinder getService(String serviceName);
	
	ServiceInfo getServiceInfo(String serviceName);
	
	ReceptacleInfo getReceptacleInfo(String receptacleName);
	
	void bindReceptacle(in ReceptacleInfo receptacleInfo, in IBinder service, in ServiceInfo serviceInfo);
	
}
