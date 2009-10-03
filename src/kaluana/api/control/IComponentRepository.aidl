package kaluana.api.control;

import kaluana.api.IComponent;
import kaluana.api.control.IComponentManager;

interface IComponentRepository {

	int loadComponent(in String componentName);
	
	int unloadComponent(in String componentName);

	void registerComponent(String componentName);
	
	void unregisterComponent(String componentName);
	
}