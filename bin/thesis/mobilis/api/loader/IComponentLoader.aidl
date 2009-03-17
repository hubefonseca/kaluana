package thesis.mobilis.api.loader;

import thesis.mobilis.api.IComponent;
import thesis.mobilis.api.IBinderCallbackListener;

interface IComponentLoader {

	IComponent getComponent(in String componentName);

	IComponent getComponentVersioned(in String componentName, in String componentVersion);
	
} 