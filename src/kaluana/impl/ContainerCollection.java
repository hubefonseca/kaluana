package kaluana.impl;
 
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import kaluana.api.IContainer;
 
public class ContainerCollection {
 
	/**
	 * Stores the component container (its facade for the world) and its name
	 */
	private ConcurrentHashMap<String, IContainer> containers;
 
	public ContainerCollection() {
		containers = new ConcurrentHashMap<String, IContainer>();
	}
 
	public void put(String name, IContainer loader) {
		containers.put(name, loader);
	}
 
	public void remove(String name) {
		containers.remove(name);
	}

	public boolean contains(String name) {
		return containers.containsKey(name);
	}
 
	public List<String> getAllNames() {
		List<String> names = new ArrayList<String>();
		for (String s : containers.keySet()) {
			names.add(s);
		}
		return names;
	}
 
	public IContainer get(String name) {
		return containers.get(name);
	}
 
	public boolean isRequestFinished(List<String> componentNames) {
		for (String name : componentNames) {
			if (!contains(name)) {
				return false;
			}
		}
		return true;
	}
 
}