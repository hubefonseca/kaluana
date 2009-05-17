package mobilis.api;

interface IService {

	IBinder getServiceImpl();
	
	void setServiceImpl(IBinder binder);
	
	void setName(String name);
	
	String getName();
	
	void setInterfaceName(String interfaceName);
	
	String getInterfaceName();
	
	void setComponentName(String componentName);
	
	String getComponentName();
	
}
