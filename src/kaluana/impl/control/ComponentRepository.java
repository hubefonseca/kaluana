package kaluana.impl.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kaluana.api.control.IComponentManager;
import kaluana.api.control.IComponentRepository;
import kaluana.api.control.IConfigService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 *  
 * @author hubertfonseca
 *
 */
public class ComponentRepository implements IComponentRepository {

	private IComponentManager listener;
	private Context applicationContext;

	private HashMap<String, List<ServiceConnection>> serviceLoaders;
	
	public ComponentRepository(Context applicationContext, IComponentManager listener) {
		this.applicationContext = applicationContext;
		this.listener = listener;
		
		serviceLoaders = new HashMap<String, List<ServiceConnection>>();
	}
	
	@Override
	public int loadComponent(String componentName) throws RemoteException {
		Log.d(this.getClass().getName(), "searching for component " + componentName + "...");

		RemoteServiceConnection mRemoteServiceConnection = new RemoteServiceConnection();

		synchronized (this) {
			// Search for the component loader service
			Intent intent = new Intent(componentName);

			if (applicationContext.getPackageManager().queryIntentServices(intent, 0).size() == 0) {
				// If no loader is found, try to download component
				try {
					Log.d(this.getClass().getName(), "component not found locally. trying to download...");
					URL url = new URL("http://10.0.2.2:8080/android/install");
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					conn.setDoOutput(true);

					OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
					out.write("componentName=" + componentName);
					out.close();

					BufferedReader in = new BufferedReader(
							new InputStreamReader(
									conn.getInputStream()));

					StringBuffer response = new StringBuffer();
					int c = in.read();
					while (c > -1) {
						response.append((char)c);
						c = in.read();
					}
					in.close();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Log.d(this.getClass().getName(), "component found locally");
			}

			if (applicationContext.getPackageManager().queryIntentServices(intent, 0).size() > 0) {
				
				// TODO: find the best component 
				applicationContext.bindService(intent, mRemoteServiceConnection, Context.BIND_AUTO_CREATE);
			} else {
				// No component found
				Log.d(this.getClass().getName(), "component not found");
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int unloadComponent(String componentName) throws RemoteException {
		List<ServiceConnection> services = serviceLoaders.get(componentName);
		for (ServiceConnection serviceConnection : services) {
			Log.i(this.getClass().getName(), "unloading service: " + serviceConnection);
			applicationContext.unbindService(serviceConnection);
		}
		return 0;
	}
	
	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This class corresponds to the connection to a LocalLoader service, and through this
	 * connection the component is achieved by other processes 
	 * @author hubertfonseca
	 *
	 */
	class RemoteServiceConnection implements ServiceConnection {

		IConfigService localLoader;
		int serviceCounter = 0;

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			try {				
				localLoader = IConfigService.Stub.asInterface(service);

				Log.i(this.getClass().getName(), "remote loader: " + localLoader.getFullName());

				// Stores the connection to this loader, so its possible to unbind it
				addService(localLoader.getFullName(), this);
				
				localLoader.registerReceptacles();
				localLoader.registerServices();

				List<String> serviceNames = new ArrayList<String>();
				localLoader.getServiceNames(serviceNames);
				serviceCounter = serviceNames.size();
				
				Log.i(this.getClass().getName(), localLoader.getFullName() + ": Remote loader bound! " + serviceCounter + " services to bind on this component");
				
				if (serviceCounter == 0) {
					// No services to bind
					listener.loaded(localLoader);
				}

				for (String serviceName : serviceNames) {
					ConfigServiceConnection mLocalServiceConnection = new ConfigServiceConnection(); 
					
					String serviceInterface = localLoader.getServiceInfo(serviceName).getInterfaceName();
					
					Intent intent = new Intent(serviceInterface);
					mLocalServiceConnection.setServiceName(serviceName);
					applicationContext.bindService(intent, mLocalServiceConnection, Context.BIND_AUTO_CREATE);
				}

			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(this.getClass().getName(), "Component stopped!");
		}

		/**
		 * This class corresponds to a Mobilis service connection, to each service
		 * provided by a Mobilis component
		 * @author hubertfonseca
		 *
		 */
		class ConfigServiceConnection implements ServiceConnection {

			private String serviceName = null;

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				try {
					localLoader.bindService(serviceName, service);
					
					// Stores the connection to the Mobilis service as well, so its possible to unbind it
					addService(localLoader.getFullName(), this);
					
					Log.i(this.getClass().getName(), "local loader: " + localLoader.getFullName());
					
					if (--serviceCounter == 0) {
						// All component's services are bound
						listener.loaded(localLoader);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				Log.i(this.getClass().getName(), "Service stopped: " + serviceName);
			}

			public void setServiceName(String name) {
				this.serviceName = name;
			}
		};
	}

	private void addService(String componentName, ServiceConnection serviceConnection) {
		List<ServiceConnection> services = serviceLoaders.get(componentName);
		if (services == null) {
			services = new ArrayList<ServiceConnection>();
		}
		services.add(serviceConnection);
		serviceLoaders.put(componentName, services);
	}
	
}