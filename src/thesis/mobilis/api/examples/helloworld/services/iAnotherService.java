/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\thesis\\src\\thesis\\mobilis\\api\\examples\\helloworld\\services\\iAnotherService.aidl
 */
package thesis.mobilis.api.examples.helloworld.services;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface iAnotherService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements thesis.mobilis.api.examples.helloworld.services.iAnotherService
{
private static final java.lang.String DESCRIPTOR = "thesis.mobilis.api.examples.helloworld.services.iAnotherService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an iAnotherService interface,
 * generating a proxy if needed.
 */
public static thesis.mobilis.api.examples.helloworld.services.iAnotherService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
thesis.mobilis.api.examples.helloworld.services.iAnotherService in = (thesis.mobilis.api.examples.helloworld.services.iAnotherService)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new thesis.mobilis.api.examples.helloworld.services.iAnotherService.Stub.Proxy(obj);
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
case TRANSACTION_getId:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getId();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements thesis.mobilis.api.examples.helloworld.services.iAnotherService
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
public int getId() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getId, _data, _reply, 0);
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
static final int TRANSACTION_getId = (IBinder.FIRST_CALL_TRANSACTION + 0);
}
public int getId() throws android.os.RemoteException;
}
