/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/hubertfonseca/java/android/workspace/mobilis/src/mobilis/api/adaptation/IAdaptationManager.aidl
 */
package mobilis.api.adaptation;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
import mobilis.context.Context;
public interface IAdaptationManager extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements mobilis.api.adaptation.IAdaptationManager
{
private static final java.lang.String DESCRIPTOR = "mobilis.api.adaptation.IAdaptationManager";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IAdaptationManager interface,
 * generating a proxy if needed.
 */
public static mobilis.api.adaptation.IAdaptationManager asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof mobilis.api.adaptation.IAdaptationManager))) {
return ((mobilis.api.adaptation.IAdaptationManager)iin);
}
return new mobilis.api.adaptation.IAdaptationManager.Stub.Proxy(obj);
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
case TRANSACTION_onContextChange:
{
data.enforceInterface(DESCRIPTOR);
mobilis.context.Context _arg0;
if ((0!=data.readInt())) {
_arg0 = mobilis.context.Context.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onContextChange(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements mobilis.api.adaptation.IAdaptationManager
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
public void onContextChange(mobilis.context.Context context) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((context!=null)) {
_data.writeInt(1);
context.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onContextChange, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onContextChange = (IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void onContextChange(mobilis.context.Context context) throws android.os.RemoteException;
}
