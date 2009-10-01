package kaluana.app;

import java.util.ArrayList;
import java.util.List;

import kaluana.api.ReceptacleInfo;
import kaluana.api.ServiceInfo;
import kaluana.api.control.IComponentManager;
import kaluana.api.control.IComponentManagerListener;
import kaluana.api.control.IConfigService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class KaluanaMapApp extends Activity implements IComponentManagerListener {

	private IConfigService sdmConfig;
	private IConfigService mapConfig;

	private IComponentManager componentManager;
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Intent intent = new Intent(kaluana.api.control.IComponentManager.class.getName());
		bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
	}

	public ServiceConnection mServiceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			try {
				componentManager = IComponentManager.Stub.asInterface(service);
				
				List<String> componentNames = new ArrayList<String>();
				componentNames.add("kaluana.sdm");
				componentNames.add("kaluana.map");
				componentManager.loadComponents(componentNames, getListener());
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
		}
	};

	public IComponentManagerListener getListener() {
		return this;
	}
		
	@Override
	public void componentsLoaded(List<String> components) throws RemoteException {
		sdmConfig = componentManager.getComponent("kaluana.sdm");
		mapConfig = componentManager.getComponent("kaluana.map");
		
		IBinder sdm = sdmConfig.getService("sdm");
		ServiceInfo serv = sdmConfig.getServiceInfo("sdm");
		ReceptacleInfo recep = mapConfig.getReceptacleInfo("sdm");
		
		mapConfig.bindReceptacle(recep, sdm, serv);
		mapConfig.start();
	}

	@Override
	public IBinder asBinder() {
		return null;
	}
	
}
