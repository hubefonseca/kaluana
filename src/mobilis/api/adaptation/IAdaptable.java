/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\mobilis\\src\\mobilis\\api\\adaptation\\IAdaptable.aidl
 */
package mobilis.api.adaptation;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface IAdaptable extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements mobilis.api.adaptation.IAdaptable
{
private static final java.lang.String DESCRIPTOR = "mobilis.api.adaptation.IAdaptable";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IAdaptable interface,
 * generating a proxy if needed.
 */
public static mobilis.api.adaptation.IAdaptable asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
mobilis.api.adaptation.IAdaptable in = (mobilis.api.adaptation.IAdaptable)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new mobilis.api.adaptation.IAdaptable.Stub.Proxy(obj);
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
case TRANSACTION_registerDependencies:
{
data.enforceInterface(DESCRIPTOR);
this.registerDependencies();
reply.writeNoException();
return true;
}
case TRANSACTION_start:
{
data.enforceInterface(DESCRIPTOR);
this.start();
reply.writeNoException();
return true;
}
case TRANSACTION_stop:
{
data.enforceInterface(DESCRIPTOR);
this.stop();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements mobilis.api.adaptation.IAdaptable
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
public void registerDependencies() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_registerDependencies, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void start() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_start, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void stop() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stop, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_registerDependencies = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_start = (IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_stop = (IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void registerDependencies() throws android.os.RemoteException;
public void start() throws android.os.RemoteException;
public void stop() throws android.os.RemoteException;
}
