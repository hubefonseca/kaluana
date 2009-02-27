/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\thesis\\src\\thesis\\mobilis\\examples\\helloworld\\services\\iAdapter.aidl
 */
package thesis.mobilis.examples.helloworld.services;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface iAdapter extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements thesis.mobilis.examples.helloworld.services.iAdapter
{
private static final java.lang.String DESCRIPTOR = "thesis.mobilis.examples.helloworld.services.iAdapter";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an iAdapter interface,
 * generating a proxy if needed.
 */
public static thesis.mobilis.examples.helloworld.services.iAdapter asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
thesis.mobilis.examples.helloworld.services.iAdapter in = (thesis.mobilis.examples.helloworld.services.iAdapter)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new thesis.mobilis.examples.helloworld.services.iAdapter.Stub.Proxy(obj);
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
case TRANSACTION_register:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
this.register(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unregister:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
this.unregister(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements thesis.mobilis.examples.helloworld.services.iAdapter
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
public void register(android.os.IBinder service) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(service);
mRemote.transact(Stub.TRANSACTION_register, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void unregister(android.os.IBinder service) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(service);
mRemote.transact(Stub.TRANSACTION_unregister, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_register = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_unregister = (IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void register(android.os.IBinder service) throws android.os.RemoteException;
public void unregister(android.os.IBinder service) throws android.os.RemoteException;
}
