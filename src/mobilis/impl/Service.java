package mobilis.impl;

import android.os.IBinder;
import android.os.RemoteException;

public class Service implements mobilis.api.IService {

	private String name;
	private IBinder binder;
	private String componentName;
	private String interfaceName;
	
	@Override
	public String getName() throws RemoteException {
		return name;
	}

	@Override
	public IBinder getServiceImpl() throws RemoteException {
		return binder;
	}

	@Override
	public void setName(String name) throws RemoteException {
		this.name = name;
	}

	@Override
	public void setServiceImpl(IBinder binder) throws RemoteException {
		this.binder = binder;
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getComponentName() throws RemoteException {
		return componentName;
	}

	@Override
	public void setComponentName(String componentName) throws RemoteException {
		this.componentName = componentName;
	}

	@Override
	public String getInterfaceName() throws RemoteException {
		return interfaceName;
	}

	@Override
	public void setInterfaceName(String interfaceName) throws RemoteException {
		this.interfaceName = interfaceName;
	}

}
