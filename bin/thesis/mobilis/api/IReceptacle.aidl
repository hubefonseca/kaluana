package thesis.mobilis.api;

interface IReceptacle {
	
	// mais de uma implementa��o (className) pode oferecer o servi�o
	
	
	void setName(String name);
	
	String getName();
	
	void connectToService(IBinder service);	
	
	void setClassName(String className);
	
	IBinder getConnection();
	
}