package mobilis.impl.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import mobilis.api.control.IComponentManager;
import mobilis.api.control.ILocalLoader;
import mobilis.api.control.IRemoteLoader;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 *  
 * @author Hubert
 *
 */
public class RemoteLoader implements IRemoteLoader {

	private IComponentManager listener;
	private ContextWrapper contextWrapper;

	public RemoteLoader(ContextWrapper contextWrapper, IComponentManager listener) {
		this.contextWrapper = contextWrapper;
		this.listener = listener;
	}
	
	@Override
	public int loadComponent(String componentName) throws RemoteException {
		Log.d(this.getClass().getName(), "searching for component " + componentName + "...");

		RemoteServiceConnection mRemoteServiceConnection = new RemoteServiceConnection();
		
		Intent intent = new Intent(componentName);
		if (contextWrapper.getPackageManager().queryIntentServices(intent, 0).size() > 0) {
			contextWrapper.bindService(intent, mRemoteServiceConnection, Context.BIND_AUTO_CREATE);
		} else {
			// If no service found, there isn`t any component like
			// that on the device. Then, try to download it from web

			Log.d(this.getClass().getName(), "component not found locally. trying to download...");
			try {
				synchronized (this) {

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
				}
				
				if (contextWrapper.getPackageManager().queryIntentServices(intent, 0).size() > 0) {
					contextWrapper.bindService(intent, mRemoteServiceConnection, Context.BIND_AUTO_CREATE);
				} else {
					// No component found
					Log.d(this.getClass().getName(), "component not found");
					return 1;
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return 0;
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	class RemoteServiceConnection implements ServiceConnection {

		ILocalLoader localLoader;
		int serviceCounter = 0;

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			try {
				localLoader = ILocalLoader.Stub.asInterface(service);

				Log.i(this.getClass().getName(), "remote loader: " + localLoader.getName());
				
				localLoader.registerReceptacles();
				localLoader.registerServices();

				List<String> serviceNames = new ArrayList<String>();
				localLoader.getServiceNames(serviceNames);
				serviceCounter = serviceNames.size();
				
				Log.i(this.getClass().getName(), localLoader.getName() + ": Remote loader bound! " + serviceCounter + " services to bind on this component");
				
				if (serviceCounter == 0) {
					// No services to bind
					listener.loaded(localLoader);
				}

				for (String serviceName : serviceNames) {
					LocalServiceConnection mLocalServiceConnection = new LocalServiceConnection(); 
					
					String serviceInterface = localLoader.getServiceInfo(serviceName).getInterfaceName();
					
					Intent intent = new Intent(serviceInterface);
					mLocalServiceConnection.setServiceName(serviceName);
					contextWrapper.bindService(intent, mLocalServiceConnection, Context.BIND_AUTO_CREATE);
				}

			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		class LocalServiceConnection implements ServiceConnection {

			private String serviceName = null;

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				try {
					localLoader.bindService(serviceName, service);
					
					Log.i(this.getClass().getName(), "local loader: " + localLoader.getName());
					
					if (--serviceCounter == 0) {
						// All the services are bound
						listener.loaded(localLoader);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub

			}

			public void setServiceName(String name) {
				this.serviceName = name;
			}
		};
	};

}