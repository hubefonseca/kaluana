package thesis.mobilis.api.impl.loader;

import thesis.mobilis.api.Component;

public class ComponentLoader {

	public static Component get(String name) 
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return (Component)Class.forName(name).newInstance();
	}

}
