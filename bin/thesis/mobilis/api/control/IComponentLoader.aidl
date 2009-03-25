package thesis.mobilis.api.control;

import thesis.mobilis.api.IComponent;
import thesis.mobilis.api.control.IComponentLoaderListener;

interface IComponentLoader {

	void loadComponent(in String componentName, in IComponentLoaderListener listener);

	void loadBestComponent(in String contextRepresentation, in IComponentLoaderListener listener);
	
}