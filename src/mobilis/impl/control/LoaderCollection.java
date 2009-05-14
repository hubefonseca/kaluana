package mobilis.impl.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import mobilis.api.control.ILocalLoader;
import android.os.RemoteException;
import android.util.Log;

public class LoaderCollection {

	/**
	 * Stores the component loader (its facade for the world) and its category
	 */
	private HashMap<String, List<ILocalLoader>> loaders;
	
	public LoaderCollection() {
		loaders = new HashMap<String, List<ILocalLoader>>();
	}
	
	public void add(String category, ILocalLoader loader) {
		List<ILocalLoader> l = loaders.get(category);
		if (l == null) {
			l = new ArrayList<ILocalLoader>();
		}
		l.add(loader);
		loaders.put(category, l);
	}
	
	public void remove(String name) {
		List<ILocalLoader> l;
		for (Entry<String, List<ILocalLoader>> entry : loaders.entrySet()) {
			l = entry.getValue();
			for (ILocalLoader loader : l) {
				try {
					if (name.endsWith("Loader")) {
						name = name.substring(0, name.lastIndexOf("Loader"));
					}
					if (name.equals(loader.getName())) {
						l.remove(loader);
						if (l.size() == 0) {
							loaders.remove(entry.getKey());
						}
						return;
					}
				} catch (RemoteException e) {
					Log.e(this.getClass().getName(), e.getMessage(), e);
				}
			}
		}
	}
	
	public ILocalLoader getByName(String name) {
		List<ILocalLoader> l;
		for (Entry<String, List<ILocalLoader>> entry : loaders.entrySet()) {
			l = entry.getValue();
			for (ILocalLoader loader : l) {
				try {
					if (name.endsWith("Loader")) {
						name = name.substring(0, name.lastIndexOf("Loader"));
					}
					if (loader.getName().equals(name)) {
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
		return loaders.keySet().contains(category);
	}
	
	public Set<String> keySet() {
		return loaders.keySet();
	}
	
	public ILocalLoader getByCategory(String category) {
		List<ILocalLoader> l = loaders.get(category);
		
		ILocalLoader loader = null;
		if (l != null && l.size() > 0) {
			loader= l.get(0);
		}
		return loader;
	}
	
	public boolean contains(String name) {
		List<ILocalLoader> l;
		for (Entry<String, List<ILocalLoader>> entry : loaders.entrySet()) {
			l = entry.getValue();
			for (ILocalLoader loader : l) {
				try {
					if (name.endsWith("Loader")) {
						name = name.substring(0, name.lastIndexOf("Loader"));
					}
					if (name.equals(loader.getName())) {
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
		List<ILocalLoader> l;
		for (Entry<String, List<ILocalLoader>> entry : loaders.entrySet()) {
			l = entry.getValue();
			for (ILocalLoader loader : l) {
				try {
					names.add(loader.getName());
				} catch (RemoteException e) {
					Log.e(this.getClass().getName(), e.getMessage(), e);
				}
			}
		}
		return names;
	}
}
