package demo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote {
    String sayHello() throws RemoteException;

    User findUser(int id) throws RemoteException;
}
