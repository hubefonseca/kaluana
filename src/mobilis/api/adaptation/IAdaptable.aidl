package mobilis.api.adaptation;

import mobilis.context.Context;

interface IAdaptable {
	
	void registerDependencies();
	
	void getDependencies();
	
	void start();
	
	void stop();
	
	boolean canOperateUnder(in Context context);
	
}