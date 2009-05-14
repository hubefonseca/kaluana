package mobilis.api;

interface IReceptacle {
	
	void setName(String name);
	
	String getName();
	
	void connectToService(IBinder service);	
	
	void setClassName(String className);
	
	IBinder getConnection();
	
}