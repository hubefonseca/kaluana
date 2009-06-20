package kaluana.api.control;

import kaluana.api.IComponent;
import kaluana.api.control.IComponentManager;

interface IRemoteLoader {

	int loadComponent(in String componentName);
	
	int unloadComponent(in String componentName);

}