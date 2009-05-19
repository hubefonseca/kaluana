package mobilis.api;

import android.os.Parcel;
import android.os.Parcelable;

public class ReceptacleInfo implements Parcelable, Comparable<ReceptacleInfo> {

	private String name;
	private String interfaceName;
	private String componentName;
	private ServiceInfo serviceInfo;

	public static final Parcelable.Creator<ReceptacleInfo> CREATOR = new Parcelable.Creator<ReceptacleInfo>() {
		public ReceptacleInfo createFromParcel(Parcel in) {
			return new ReceptacleInfo(in);
		}

		public ReceptacleInfo[] newArray(int size) {
			return new ReceptacleInfo[size];
		}
	};
	
	public ReceptacleInfo(String name, String interfaceName, String componentName) {
		this.name = name;
		this.interfaceName = interfaceName;
		this.componentName = componentName;
	}
	
	public ReceptacleInfo(Parcel in) {
		readFromParcel(in);
	}

	public void readFromParcel(Parcel in) {
		this.name = in.readString();
		this.interfaceName = in.readString();
		this.componentName = in.readString();
		this.serviceInfo = in.readParcelable(ServiceInfo.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(name);
		arg0.writeString(interfaceName);
		arg0.writeString(componentName);
		arg0.writeParcelable(serviceInfo, 0);
	}
	
	@Override
	public int compareTo(ReceptacleInfo another) {
		return name.compareTo(another.toString());
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public ServiceInfo getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(ServiceInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public boolean isBound() {
		return serviceInfo != null;
	}
	
}