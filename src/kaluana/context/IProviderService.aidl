package kaluana.context;

import kaluana.api.adaptation.IAdaptationManager;
import kaluana.context.IContextListener;

interface IProviderService {
	
	void start();
	
	void registerAdaptationManager(IAdaptationManager adaptationManager);
	
}