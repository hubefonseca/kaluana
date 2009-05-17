package mobilis.api;

import mobilis.api.IService;
import mobilis.context.location.ISemanticLocationService;

interface IReceptacle {
	
	void setName(String receptacleName);
	
	String getName();
	
	void connectToService(IService serviceImpl);
	
	void setClassName(String className);
	
	IService getConnection();
	
}
