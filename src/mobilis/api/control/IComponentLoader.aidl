package mobilis.api.control;

import mobilis.api.IComponent;
import mobilis.api.control.IComponentLoaderListener;

interface IComponentLoader {

	void loadComponent(in String componentName, in IComponentLoaderListener listener);

	void loadBestComponent(in String contextRepresentation, in IComponentLoaderListener listener);
	
}