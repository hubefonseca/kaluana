package thesis.mobilis.api;

import thesis.mobilis.api.IReceptacle;

interface IComponent {

	void registerService(String serviceName);
	
	void registerReceptacle(String receptacleName);
	
	void getServiceNames(out List<String> serviceNames);
	
	void getReceptacleNames(out List<String> receptacleNames);
	
	IBinder getService(String serviceName);
	
	IReceptacle getReceptacle(String receptacleName);
	
	void startup();
	
	void shutdown();
	
}