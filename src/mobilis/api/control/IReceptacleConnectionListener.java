/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\mobilis\\src\\mobilis\\api\\control\\IReceptacleConnectionListener.aidl
 */
package mobilis.api.control;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface IReceptacleConnectionListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements mobilis.api.control.IReceptacleConnectionListener
{
private static final java.lang.String DESCRIPTOR = "mobilis.api.control.IReceptacleConnectionListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IReceptacleConnectionListener interface,
 * generating a proxy if needed.
 */
public static mobilis.api.control.IReceptacleConnectionListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
mobilis.api.control.IReceptacleConnectionListener in = (mobilis.api.control.IReceptacleConnectionListener)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new mobilis.api.control.IReceptacleConnectionListener.Stub.Proxy(obj);
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
case TRANSACTION_connected:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.connected(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_disconnected:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.disconnected(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements mobilis.api.control.IReceptacleConnectionListener
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
public void connected(java.lang.String receptacleName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(receptacleName);
mRemote.transact(Stub.TRANSACTION_connected, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void disconnected(java.lang.String receptacleName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(receptacleName);
mRemote.transact(Stub.TRANSACTION_disconnected, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_connected = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_disconnected = (IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void connected(java.lang.String receptacleName) throws android.os.RemoteException;
public void disconnected(java.lang.String receptacleName) throws android.os.RemoteException;
}
