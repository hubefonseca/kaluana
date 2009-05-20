package mobilis.api.control;

import mobilis.api.IComponent;
import mobilis.api.control.IComponentManager;

interface IRemoteLoader {

	int loadComponent(in String componentName);
	
	int unloadComponent(in String componentName);

}