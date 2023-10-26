package Serveur;
import java.net.*;
import java.io.*;
import java.io.IOException;

public class ServeurTCP {
    public static void main(String args[]) throws IOException
    {
        ServerSocket Ssocket;
        Socket Csocket;
        Ssocket = new ServerSocket(50000);
        System.out.println("attente de la premiere connexion....");
        Csocket = Ssocket.accept();
        System.out.println("Connexion établie");
        System.out.println("--------ServerSocket-------");
        System.out.println("Adresse IP locale : " + Ssocket.getInetAddress().getHostAddress());
        System.out.println("Port local : " +Ssocket.getLocalPort());
        System.out.println("-----------Socket------------");
        System.out.println("Adresse IP locale : " + Ssocket.getInetAddress().getHostAddress());
        System.out.println("Port local : " +Csocket.getLocalPort());
        System.out.println("Port distant : " + Csocket.getPort());
        System.out.println("fermeture de connexion");
        Ssocket.close();
        Csocket.close();
        System.out.println("attente de connexion avec Time out");
        Ssocket = new ServerSocket(50000);
        Ssocket.setSoTimeout(3000);
        System.out.println("Attente d'une 2eme connexion.....");
        try
        {
            Csocket = Ssocket.accept();
            System.out.println("Connexion etablie");
            Ssocket.close();
            Csocket.close();
        }
        catch (SocketTimeoutException e)
        {
            System.out.println("Time out : "+e.getMessage());
        }
    }
}
