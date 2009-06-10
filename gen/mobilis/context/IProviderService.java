/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/hubertfonseca/java/android/workspace/mobilis/src/mobilis/context/IProviderService.aidl
 */
package mobilis.context;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
import mobilis.api.adaptation.IAdaptationManager;
public interface IProviderService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements mobilis.context.IProviderService
{
private static final java.lang.String DESCRIPTOR = "mobilis.context.IProviderService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IProviderService interface,
 * generating a proxy if needed.
 */
public static mobilis.context.IProviderService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof mobilis.context.IProviderService))) {
return ((mobilis.context.IProviderService)iin);
}
return new mobilis.context.IProviderService.Stub.Proxy(obj);
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
case TRANSACTION_start:
{
data.enforceInterface(DESCRIPTOR);
this.start();
reply.writeNoException();
return true;
}
case TRANSACTION_registerAdaptationManager:
{
data.enforceInterface(DESCRIPTOR);
mobilis.api.adaptation.IAdaptationManager _arg0;
_arg0 = mobilis.api.adaptation.IAdaptationManager.Stub.asInterface(data.readStrongBinder());
this.registerAdaptationManager(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements mobilis.context.IProviderService
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
public void registerAdaptationManager(mobilis.api.adaptation.IAdaptationManager adaptationManager) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((adaptationManager!=null))?(adaptationManager.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerAdaptationManager, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_start = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_registerAdaptationManager = (IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void start() throws android.os.RemoteException;
public void registerAdaptationManager(mobilis.api.adaptation.IAdaptationManager adaptationManager) throws android.os.RemoteException;
}
