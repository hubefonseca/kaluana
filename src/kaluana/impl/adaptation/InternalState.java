package kaluana.impl.adaptation;

import android.os.Parcel;
import android.os.Parcelable;

public class InternalState implements Parcelable {

	private String myInfo;
	
	public String getMyInfo() {
		return myInfo;
	}

	public void setMyInfo(String myInfo) {
		this.myInfo = myInfo;
	}

	public static final Parcelable.Creator<InternalState> CREATOR = new Parcelable.Creator<InternalState>() {
		public InternalState createFromParcel(Parcel in) {
			return new InternalState(in);
		}

		public InternalState[] newArray(int size) {
			return new InternalState[size];
		}
	};

	public InternalState() {
		
	}
	
	public InternalState(Parcel in) {
		readFromParcel(in);
	}

	public void readFromParcel(Parcel in) {
		myInfo = in.readString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(myInfo);
	}

}