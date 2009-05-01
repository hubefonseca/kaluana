package mobilis.api.adaptation;

import mobilis.context.ExecutionScope;

interface IAdaptable {
	
	void registerDependencies();
	
	void getDependencies();
	
	void start();
	
	void stop();
	
	void registerExecutionScope(in ExecutionScope executionScope);
	
	void registerScope();
	
}