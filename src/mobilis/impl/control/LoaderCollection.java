package mobilis.impl.control;

import java.util.ArrayList;
import java.util.List;

import mobilis.api.control.ILocalLoader;
import android.os.RemoteException;
import android.util.Log;

public class LoaderCollection {

	private List<ILocalLoader> loaders;
	
	public LoaderCollection() {
		loaders = new ArrayList<ILocalLoader>();
	}
	
	public void add(ILocalLoader loader) {
		loaders.add(loader);
	}
	
	public void remove(String loaderName) throws RemoteException {
		for(ILocalLoader loader : loaders) {
			if (loader.getName().equals(loaderName)) {
				loaders.remove(loader);
				break;
			}
		}
	}
	
	public ILocalLoader getByName(String loaderName) throws RemoteException {
		for (ILocalLoader loader : loaders) {
			if (loader.getName().equals(loaderName)) {
				return loader;
			}
		}
		return null;
	}
	
	public boolean contains(String loaderName) throws RemoteException {
		for (ILocalLoader loader : loaders) {
			if (loader.getName().equals(loaderName)) {
				return true;
			}
		}
		return false;
	}
	
	public List<String> getComponentNames() {
		List<String> componentNames = new ArrayList<String>();
		for (ILocalLoader loader : loaders) {
			try {
				Log.d(this.getClass().getName(), loader.getName());
				componentNames.add(loader.getName());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return componentNames;
	}
}
