package mobilis.impl.control;

import java.util.ArrayList;
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
		for(IComponent component : components) {
			if (component.getName().equals(componentName)) {
				components.remove(component);
				break;
			}
		}
	}
	
	public IComponent getByName(String componentName) throws RemoteException {
		for (IComponent component : components) {
			if (component.getName().equals(componentName)) {
				return component;
			}
		}
		return null;
	}
	
	public boolean contains(String componentName) throws RemoteException {
		for (IComponent component : components) {
			if (component.getName().equals(componentName)) {
				return true;
			}
		}
		return false;
	}
	
	public List<String> getComponentNames() {
		List<String> componentNames = new ArrayList<String>();
		for (IComponent component : components) {
			try {
				componentNames.add(component.getName());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return componentNames;
	}
}
