package thesis.mobilis.api;

import thesis.mobilis.api.IReceptacle;

interface IComponent {
	
	// fazer o bind no Receptacle impede recept�culos m�ltiplos?
	
	/*
	 * Must be implemented by component developer
	 */
	void registerServices();
	
	void registerReceptacles();
	
	void registerDependencies();
	
	void start();
	
	void stop();
	
	
	/*
	 * May be called by component developer
	 */
	void registerService(String serviceName, String interfaceName);
	
	void registerReceptacle(String receptacleName, String interfaceName);
	
	void registerDependency(String componentName);
	
	/*
	 * May be called by component user
	 */
	void getServiceNames(out List<String> serviceNames);
	
	void getReceptacleNames(out List<String> receptacleNames);
	
	IBinder getService(String serviceName);
	
	IReceptacle getReceptacle(String receptacleName);
	
}