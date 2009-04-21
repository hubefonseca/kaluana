package mobilis.impl.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mobilis.api.IComponent;

import android.os.RemoteException;

public class ComponentCollection {

	private List<IComponent> components;
	
	public ComponentCollection() {
		components = new ArrayList<IComponent>();
	}
	
	public void add(IComponent component) {
		components.add(component);
	}
	
	public void remove(String componentName) throws RemoteException {
		Iterator<IComponent> iterator = components.iterator();
		IComponent component;
		while (iterator.hasNext()) {
			component = iterator.next();
			if (component.getName().equals(componentName)) {
				components.remove(component);
				break;
			}
		}
	}
	
	public IComponent getByName(String componentName) throws RemoteException {
		Iterator<IComponent> iterator = components.iterator();
		IComponent component;
		while (iterator.hasNext()) {
			component = iterator.next();
			if (component.getName().equals(componentName)) {
				return component;
			}
		}
		return null;
	}
	
	public boolean contains(String componentName) throws RemoteException {
		Iterator<IComponent> iterator = components.iterator();
		IComponent component;
		while (iterator.hasNext()) {
			component = iterator.next();
			if (component.getName().equals(componentName)) {
				return true;
			}
		}
		return false;
	}
	
}
