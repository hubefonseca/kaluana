package mobilis.api.control;

import mobilis.api.IReceptacle;
import mobilis.api.IComponent;
import mobilis.api.IService;

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
	
	String getServiceInterface(String serviceName);
	
	void getReceptacleNames(out List<String> receptacleNames);
	
	IBinder getService(String serviceName);
	
	IReceptacle getReceptacle(String receptacleName);
	
	void bindReceptacle(String receptacleName, IBinder binder, String serviceName, String providerComponentName);
	
	IService getBoundService(String receptacleName);
	
}
