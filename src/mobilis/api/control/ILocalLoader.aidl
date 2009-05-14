package mobilis.api.control;

import mobilis.api.IReceptacle;
import mobilis.api.IComponent;

interface ILocalLoader {

	/*
	 * Called by the middleware
	 */
	String getName();
	
	String getCategory();
	
	void bind(String serviceName, IBinder service);
	
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
	
	IReceptacle getReceptacle(String receptacleName);
	
}
