package mobilis.api;

import mobilis.api.ReceptacleInfo;
import mobilis.api.ServiceInfo;

interface IComponent {

	/*
	* Internal
	*/
	void bindService(String serviceName, IBinder service);
	
	String getName();
	
	void start();
	
	void stop();
	
	/*
	 * Must be implemented by component developer
	 */
	void registerServices();
	
	void registerReceptacles();
	
	String getCategory();
	
	/*
	 * May be called by component developer
	 */
	void registerService(String serviceName, String interfaceName);
	
	void registerReceptacle(String receptacleName, String interfaceName);
	
	void registerDependency(String componentName);
	
	/*
	 * Should be called by component user
	 */
	void getServiceNames(out List<String> serviceNames);
	
	void getReceptacleNames(out List<String> receptacleNames);
	
	IBinder getService(String serviceName);
	
	ServiceInfo getServiceInfo(String serviceName);
	
	ReceptacleInfo getReceptacleInfo(String receptacleName);
	
	void bindReceptacle(in ReceptacleInfo receptacleInfo, in IBinder service, in ServiceInfo serviceInfo);
	
	IBinder getBoundService(String receptacleName);
	
}
