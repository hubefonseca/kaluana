package thesis.mobilis.api;

import thesis.mobilis.api.IBinderCallbackListener;

interface IReceptacle {
	
	void setName(String name);
	
	String getName();
	
	void connectToService(IBinder service);	
	
	void setClassName(String className);
	
	IBinder getConnection();
	
}