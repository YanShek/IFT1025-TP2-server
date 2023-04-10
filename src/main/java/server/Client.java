package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = ServerLauncher.PORT;
        Scanner sc = new Scanner(System.in);

        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            while (true){
                System.out.println("Entrez une commande: ");
                String commande = sc.nextLine();

                if (commande.equals(("charger").toLowerCase())){
                    System.out.println("Entrez la session");
                }
            }
        }
        catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }
}
