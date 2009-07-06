package kaluana.api.control;

import kaluana.api.IComponent;
import kaluana.api.ServiceInfo;
import kaluana.api.ReceptacleInfo;
import kaluana.impl.adaptation.InternalState;

interface ILocalLoader {

	/*
	 * Called by the middleware
	 */
	String getSimpleName();
	
	String getFullName();
	
	String getCategory();
	
	void bindService(String serviceName, IBinder binder);
	
	void start();
	
	void stop();
	
	InternalState getInternalState();
	
	void setInternalState(in InternalState internalState);
	
	void registerServices();
	
	void registerReceptacles();
	
	void getDependencies(out List<String> dependencies);

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
