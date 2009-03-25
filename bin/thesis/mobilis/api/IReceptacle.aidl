package thesis.mobilis.api;

interface IReceptacle {
	
	// mais de uma implementação (className) pode oferecer o serviço
	
	
	void setName(String name);
	
	String getName();
	
	void connectToService(IBinder service);	
	
	void setClassName(String className);
	
	IBinder getConnection();
	
}