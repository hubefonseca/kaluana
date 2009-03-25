package thesis.mobilis.impl.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import thesis.mobilis.api.IComponent;
import thesis.mobilis.api.control.IComponentCollection;

public class ComponentCollection implements IComponentCollection {

	private List<IComponent> components;

	public ComponentCollection() {
		components = new ArrayList<IComponent>();
	}
	
	@Override
	public IComponent getByName(String componentName) throws RemoteException {
		Iterator<IComponent> iterator = components.iterator();
		IComponent component;
		while (iterator.hasNext()) {
			component = iterator.next();
			if (component.getName().equals(componentName))
				return component;
		}
		return null;
	}

	@Override
	public void add(IComponent component) throws RemoteException {
		components.add(component);
	}
	
	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void list(List<String> componentNames) throws RemoteException {
		Iterator<IComponent> iterator = components.iterator();
		IComponent component;
		while (iterator.hasNext()) {
			component = iterator.next();
			componentNames.add(component.getName());
		}
	}

}
