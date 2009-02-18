package thesis.mobilis.api.impl.object;

import android.os.Parcel;
import android.os.Parcelable;
 
public class Object implements Parcelable {
 
  private int id;
 
  public static final Parcelable.Creator<Object> CREATOR = new Parcelable.Creator<Object>() {
        public Object createFromParcel(Parcel in) {
            return new Object(in);
        }
        
        public Object[] newArray(int size) {
            return new Object[size];
        }
    };
 
    public Object(int id) {
      this.id = id;
    }
    
    public Object(Parcel in) {
      readFromParcel(in);
    }
    
    public void readFromParcel(Parcel in) {
      this.id = in.readInt();
    }
    
  @Override
  public int describeContents() {
    // TODO Auto-generated method stub
    return 0;
  }
 
  @Override
  public void writeToParcel(Parcel arg0, int arg1) {
    // TODO Auto-generated method stub
    arg0.writeInt(id);
  }
 
  public int getId() {
    return id;
  }
 
  public void setId(int id) {
    this.id = id;
  }
}