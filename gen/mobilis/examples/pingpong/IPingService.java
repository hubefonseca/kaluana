/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/hubertfonseca/java/android/workspace/mobilis/src/mobilis/examples/pingpong/IPingService.aidl
 */
package mobilis.examples.pingpong;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface IPingService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements mobilis.examples.pingpong.IPingService
{
private static final java.lang.String DESCRIPTOR = "mobilis.examples.pingpong.IPingService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IPingService interface,
 * generating a proxy if needed.
 */
public static mobilis.examples.pingpong.IPingService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof mobilis.examples.pingpong.IPingService))) {
return ((mobilis.examples.pingpong.IPingService)iin);
}
return new mobilis.examples.pingpong.IPingService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_ping:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.ping();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements mobilis.examples.pingpong.IPingService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public int ping() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_ping, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_ping = (IBinder.FIRST_CALL_TRANSACTION + 0);
}
public int ping() throws android.os.RemoteException;
}
