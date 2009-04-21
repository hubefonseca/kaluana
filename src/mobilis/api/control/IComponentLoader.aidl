package mobilis.api.control;

import mobilis.api.IComponent;
import mobilis.api.control.IComponentManager;

interface IComponentLoader {

	void loadComponent(in String componentName, in IComponentManager listener);

	void loadBestComponent(in String contextRepresentation, in IComponentManager listener);
	
}