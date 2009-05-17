package mobilis.api;

import mobilis.api.IReceptacle;
import mobilis.api.IService;

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
	
	String getServiceInterface(String serviceName);
	
	void getReceptacleNames(out List<String> receptacleNames);
	
	IService getService(String serviceName);
	
	IReceptacle getReceptacle(String receptacleName);
	
	void bindReceptacle(String receptacleName, IService service);
	
	IService getBoundService(String receptacleName);
	
}
