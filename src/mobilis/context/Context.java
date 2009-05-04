package mobilis.context;

import android.os.Parcel;
import android.os.Parcelable;

public class Context implements Parcelable {

	private double latitude;
	private double longitude;
	private String location;

	public static final Parcelable.Creator<Context> CREATOR = new Parcelable.Creator<Context>() {
		public Context createFromParcel(Parcel in) {
			return new Context(in);
		}

		public Context[] newArray(int size) {
			return new Context[size];
		}
	};

	public Context() {
		
	}
	
	public Context(Parcel in) {
		readFromParcel(in);
	}

	public void readFromParcel(Parcel in) {
		this.latitude = in.readDouble();
		this.longitude = in.readDouble();
		this.location = in.readString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeDouble(latitude);
		arg0.writeDouble(longitude);
		arg0.writeString(location);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}