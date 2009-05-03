package mobilis.api;

import mobilis.api.IReceptacle;

interface IComponent {

	/*
	* Internal
	*/
	String getName();
	
	/*
	 * Must be implemented by component developer
	 */
	void registerServices();
	
	void registerReceptacles();
	
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
	
	IReceptacle getReceptacle(String receptacleName);
	
}
