/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\thesis\\src\\thesis\\mobilis\\api\\IComponent.aidl
 */
package thesis.mobilis.api;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
import java.util.List;
public interface IComponent extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements thesis.mobilis.api.IComponent
{
private static final java.lang.String DESCRIPTOR = "thesis.mobilis.api.IComponent";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IComponent interface,
 * generating a proxy if needed.
 */
public static thesis.mobilis.api.IComponent asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
thesis.mobilis.api.IComponent in = (thesis.mobilis.api.IComponent)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new thesis.mobilis.api.IComponent.Stub.Proxy(obj);
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
case TRANSACTION_registerService:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.registerService(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_registerReceptacle:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.registerReceptacle(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getServiceNames:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _arg0;
_arg0 = new java.util.ArrayList<java.lang.String>();
this.getServiceNames(_arg0);
reply.writeNoException();
reply.writeStringList(_arg0);
return true;
}
case TRANSACTION_getReceptacleNames:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _arg0;
_arg0 = new java.util.ArrayList<java.lang.String>();
this.getReceptacleNames(_arg0);
reply.writeNoException();
reply.writeStringList(_arg0);
return true;
}
case TRANSACTION_getService:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.IBinder _result = this.getService(_arg0);
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_getReceptacle:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
thesis.mobilis.api.IReceptacle _result = this.getReceptacle(_arg0);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
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
private static class Proxy implements thesis.mobilis.api.IComponent
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
// tem que oferecer métodos para que o component manager
// registre as facetas e receptáculos ao chamar o componente
// fazer o bind no Receptacle impede receptáculos múltiplos?

public void registerService(java.lang.String serviceName, java.lang.String interfaceName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(serviceName);
_data.writeString(interfaceName);
mRemote.transact(Stub.TRANSACTION_registerService, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void registerReceptacle(java.lang.String receptacleName, java.lang.String interfaceName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(receptacleName);
_data.writeString(interfaceName);
mRemote.transact(Stub.TRANSACTION_registerReceptacle, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void getServiceNames(java.util.List<java.lang.String> serviceNames) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getServiceNames, _data, _reply, 0);
_reply.readException();
_reply.readStringList(serviceNames);
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void getReceptacleNames(java.util.List<java.lang.String> receptacleNames) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getReceptacleNames, _data, _reply, 0);
_reply.readException();
_reply.readStringList(receptacleNames);
}
finally {
_reply.recycle();
_data.recycle();
}
}
public android.os.IBinder getService(java.lang.String serviceName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(serviceName);
mRemote.transact(Stub.TRANSACTION_getService, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public thesis.mobilis.api.IReceptacle getReceptacle(java.lang.String receptacleName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
thesis.mobilis.api.IReceptacle _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(receptacleName);
mRemote.transact(Stub.TRANSACTION_getReceptacle, _data, _reply, 0);
_reply.readException();
_result = thesis.mobilis.api.IReceptacle.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
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
static final int TRANSACTION_registerService = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_registerReceptacle = (IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getServiceNames = (IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getReceptacleNames = (IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getService = (IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getReceptacle = (IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_start = (IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_stop = (IBinder.FIRST_CALL_TRANSACTION + 7);
}
// tem que oferecer métodos para que o component manager
// registre as facetas e receptáculos ao chamar o componente
// fazer o bind no Receptacle impede receptáculos múltiplos?

public void registerService(java.lang.String serviceName, java.lang.String interfaceName) throws android.os.RemoteException;
public void registerReceptacle(java.lang.String receptacleName, java.lang.String interfaceName) throws android.os.RemoteException;
public void getServiceNames(java.util.List<java.lang.String> serviceNames) throws android.os.RemoteException;
public void getReceptacleNames(java.util.List<java.lang.String> receptacleNames) throws android.os.RemoteException;
public android.os.IBinder getService(java.lang.String serviceName) throws android.os.RemoteException;
public thesis.mobilis.api.IReceptacle getReceptacle(java.lang.String receptacleName) throws android.os.RemoteException;
public void start() throws android.os.RemoteException;
public void stop() throws android.os.RemoteException;
}
