/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\mobilis\\src\\mobilis\\api\\IReceptacle.aidl
 */
package mobilis.api;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface IReceptacle extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements mobilis.api.IReceptacle
{
private static final java.lang.String DESCRIPTOR = "mobilis.api.IReceptacle";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IReceptacle interface,
 * generating a proxy if needed.
 */
public static mobilis.api.IReceptacle asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
mobilis.api.IReceptacle in = (mobilis.api.IReceptacle)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new mobilis.api.IReceptacle.Stub.Proxy(obj);
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
case TRANSACTION_setName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.setName(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getName();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_connectToService:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
this.connectToService(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setClassName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.setClassName(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getConnection:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _result = this.getConnection();
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements mobilis.api.IReceptacle
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
// mais de uma implementação (className) pode oferecer o serviço

public void setName(java.lang.String name) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
mRemote.transact(Stub.TRANSACTION_setName, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public java.lang.String getName() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getName, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public void connectToService(android.os.IBinder service) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(service);
mRemote.transact(Stub.TRANSACTION_connectToService, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void setClassName(java.lang.String className) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(className);
mRemote.transact(Stub.TRANSACTION_setClassName, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public android.os.IBinder getConnection() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getConnection, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_setName = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getName = (IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_connectToService = (IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_setClassName = (IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getConnection = (IBinder.FIRST_CALL_TRANSACTION + 4);
}
// mais de uma implementação (className) pode oferecer o serviço

public void setName(java.lang.String name) throws android.os.RemoteException;
public java.lang.String getName() throws android.os.RemoteException;
public void connectToService(android.os.IBinder service) throws android.os.RemoteException;
public void setClassName(java.lang.String className) throws android.os.RemoteException;
public android.os.IBinder getConnection() throws android.os.RemoteException;
}
