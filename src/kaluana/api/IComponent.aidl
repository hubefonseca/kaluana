package kaluana.api;

import kaluana.api.ReceptacleInfo;
import kaluana.api.ServiceInfo;
import kaluana.impl.adaptation.InternalState;

interface IComponent {

	String getSimpleName();
	
	String getFullName();

	void getServiceNames(out List<String> serviceNames);
	
	void getReceptacleNames(out List<String> receptacleNames);
	
	IBinder getService(String serviceName);
	
	ServiceInfo getServiceInfo(String serviceName);
	
	ReceptacleInfo getReceptacleInfo(String receptacleName);
	
	void bindReceptacle(in ReceptacleInfo receptacleInfo, in IBinder service, in ServiceInfo serviceInfo);
	
	InternalState getInternalState();
	
	void setInternalState(in InternalState componentInfo);
	
	void start();
	
	void stop();
	
	void onActive();
	
	void onInactive();
	
}
