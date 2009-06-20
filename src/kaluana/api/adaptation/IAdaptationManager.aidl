package kaluana.api.adaptation;

import kaluana.api.IComponent;
import kaluana.context.Context;

interface IAdaptationManager {
	
	void onContextChange(in Context context);
	
}