package demo.rmi.server;

import demo.rmi.User;
import demo.rmi.UserService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Server implements UserService {

    public static void main(String args[]) {
        try {
            Server obj = new Server();
            UserService stub = (UserService) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("UserService", stub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public String sayHello() {
        return "Hello, world!";
    }

    @Override
    public User findUser(int id) throws RemoteException {
        Map<Integer, User> systemUsers = new HashMap<Integer, User>();
        systemUsers.put(1, new User(1, "Bob", "mypass"));
        systemUsers.put(2, new User(2, "Lisa", "pass1"));
        systemUsers.put(3, new User(3, "Richie", "pass2"));

        return systemUsers.get(id);
    }
}
