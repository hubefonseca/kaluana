/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\thesis\\src\\thesis\\mobilis\\api\\examples\\helloworld\\services\\iMobService.aidl
 */
package thesis.mobilis.api.examples.helloworld.services;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
import thesis.mobilis.api.impl.object.Object;
public interface iMobService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements thesis.mobilis.api.examples.helloworld.services.iMobService
{
private static final java.lang.String DESCRIPTOR = "thesis.mobilis.api.examples.helloworld.services.iMobService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an iMobService interface,
 * generating a proxy if needed.
 */
public static thesis.mobilis.api.examples.helloworld.services.iMobService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
thesis.mobilis.api.examples.helloworld.services.iMobService in = (thesis.mobilis.api.examples.helloworld.services.iMobService)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new thesis.mobilis.api.examples.helloworld.services.iMobService.Stub.Proxy(obj);
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
case TRANSACTION_get:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.get();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getAnObject:
{
data.enforceInterface(DESCRIPTOR);
thesis.mobilis.api.impl.object.Object _result = this.getAnObject();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements thesis.mobilis.api.examples.helloworld.services.iMobService
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
public int get() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_get, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public thesis.mobilis.api.impl.object.Object getAnObject() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
thesis.mobilis.api.impl.object.Object _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAnObject, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = thesis.mobilis.api.impl.object.Object.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_get = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getAnObject = (IBinder.FIRST_CALL_TRANSACTION + 1);
}
public int get() throws android.os.RemoteException;
public thesis.mobilis.api.impl.object.Object getAnObject() throws android.os.RemoteException;
}
