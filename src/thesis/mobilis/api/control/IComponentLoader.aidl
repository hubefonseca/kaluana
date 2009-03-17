package thesis.mobilis.api.control;

import thesis.mobilis.api.IComponent;

interface IComponentLoader {

	IComponent getComponent(in String componentName);

	IComponent getComponentVersioned(in String componentName, in String componentVersion);
	
}