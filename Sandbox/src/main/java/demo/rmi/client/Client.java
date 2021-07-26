package demo.rmi.client;

import demo.rmi.User;
import demo.rmi.UserService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {
        String host = "localhost";
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            UserService stub = (UserService) registry.lookup("UserService");
            String response = stub.sayHello();
            System.out.println("Client recieving response : " + response);

            User foundUser = stub.findUser(2);
            System.out.println("User" + foundUser);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
