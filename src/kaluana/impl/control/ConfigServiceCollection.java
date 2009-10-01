package kaluana.impl.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import kaluana.api.control.IConfigService;
import android.os.RemoteException;
import android.util.Log;

public class ConfigServiceCollection {

	/**
	 * Stores the component configuration service (its facade for the world) and its category
	 */
	private HashMap<String, List<IConfigService>> configs;
	
	public ConfigServiceCollection() {
		configs = new HashMap<String, List<IConfigService>>();
	}
	
	public void add(String category, IConfigService loader) {
		synchronized(this) {
			List<IConfigService> l = configs.get(category);
			if (l == null) {
				l = new ArrayList<IConfigService>();
			}
			l.add(loader);
			configs.put(category, l);
		}
	}
	
	public void remove(String name) {
		List<IConfigService> l;
		synchronized(this) {
			for (Entry<String, List<IConfigService>> entry : configs.entrySet()) {
				l = entry.getValue();
				for (IConfigService loader : l) {
					try {
						if (name.endsWith("Config")) {
							name = name.substring(0, name.lastIndexOf("Config"));
						}
						if (name.equals(loader.getFullName())) {
							l.remove(loader);
							if (l.size() == 0) {
								configs.remove(entry.getKey());
							}
							return;
						}
					} catch (RemoteException e) {
						Log.e(this.getClass().getName(), e.getMessage(), e);
					}
				}
			}
		}
	}
	
	public IConfigService getByName(String name) {
		List<IConfigService> l;
		for (Entry<String, List<IConfigService>> entry : configs.entrySet()) {
			l = entry.getValue();
			for (IConfigService loader : l) {
				try {
					if (name.endsWith("Config")) {
						name = name.substring(0, name.lastIndexOf("Config"));
					}
					if (loader.getFullName().equals(name)) {
						return loader;
					}
				} catch (RemoteException e) {
					Log.e(this.getClass().getName(), e.getMessage(), e);
				}
			}
		}
		return null;
	}
	
	public boolean containsCategory(String category) {
		return configs.keySet().contains(category);
	}
	
	public Set<String> keySet() {
		return configs.keySet();
	}
	
	public IConfigService getByCategory(String category) {
		List<IConfigService> l = configs.get(category);
		
		IConfigService loader = null;
		if (l != null && l.size() > 0) {
			loader = l.get(0);
		}
		return loader;
	}
	
	public boolean contains(String name) {
		List<IConfigService> l;
		for (Entry<String, List<IConfigService>> entry : configs.entrySet()) {
			l = entry.getValue();
			for (IConfigService loader : l) {
				try {
					if (name.endsWith("Config")) {
						name = name.substring(0, name.lastIndexOf("Config"));
					}
					if (name.equals(loader.getFullName())) {
						return true;
					}
				} catch (RemoteException e) {
					Log.e(this.getClass().getName(), e.getMessage(), e);
				}
			}
		}
		return false;
	}
	
	public List<String> getAllNames() {
		List<String> names = new ArrayList<String>();
		List<IConfigService> l;
		for (Entry<String, List<IConfigService>> entry : configs.entrySet()) {
			l = entry.getValue();
			for (IConfigService loader : l) {
				try {
					names.add(loader.getFullName());
				} catch (RemoteException e) {
					Log.e(this.getClass().getName(), e.getMessage(), e);
				}
			}
		}
		return names;
	}
	
	public List<IConfigService> get(String category) {
		return configs.get(category);
	}
	
	public boolean isRequestFinished(List<String> componentNames) {
		boolean requestComplete = true;
		for (String name : componentNames) {
			if (!containsCategory(name) && !contains(name)) {
				requestComplete = false;
				break;
			}
		}
		return requestComplete;
	}
	
}
