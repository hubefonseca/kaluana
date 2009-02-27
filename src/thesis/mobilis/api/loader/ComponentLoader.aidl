package thesis.mobilis.api.loader;

import thesis.mobilis.api.Component;

interface ComponentLoader {

	Component getComponent(in String componentName);

	Component getComponentVersioned(in String componentName, in String componentVersion);
	
} 