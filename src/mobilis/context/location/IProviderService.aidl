package mobilis.context.location;

import mobilis.context.location.ILocationListener;

interface IProviderService {
	
	void registerListener(ILocationListener listener);

}