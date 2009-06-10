package mobilis.context;

import mobilis.api.adaptation.IAdaptationManager;
import mobilis.context.IContextListener;

interface IProviderService {
	
	void start();
	
	void registerAdaptationManager(IAdaptationManager adaptationManager);
	
}