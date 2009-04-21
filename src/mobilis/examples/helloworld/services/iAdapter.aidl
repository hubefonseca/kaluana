package mobilis.examples.helloworld.services;

import mobilis.examples.helloworld.services.iAnotherService;

interface iAdapter {

	void register(IBinder service);
	
	void unregister(IBinder service);
	
}