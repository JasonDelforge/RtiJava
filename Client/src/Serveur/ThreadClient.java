package Serveur;

import java.io.*;
import java.net.Socket;

public abstract class ThreadClient extends Thread
{
    protected Protocole protocole;
    protected Socket Csocket;
    protected Logger logger;
    private int numero;
    private static int numCourant = 1;
    public ThreadClient(Protocole protocole,Socket csocket,Logger logger) throws IOException
    {
        super("Th Client" + numCourant + "(protocole= " + protocole.getNom() + ")" );
        this.protocole= protocole;
        this.Csocket = csocket;
        this.logger = logger;
        this.numero = numCourant;
    }
    public ThreadClient(Protocole protocole,ThreadGroup groupe, Logger logger) throws  IOException
    {
        super(groupe,"TH Client " + numCourant + " (protocole= " + protocole.getNom() + ")");
        this.protocole = protocole;
        this.Csocket = null;
        this.logger = logger;
        this.numero = numCourant++;
    }
    @Override
    public void run()
    {
        try
        {
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            try
            {
                ois = new ObjectInputStream(Csocket.getInputStream());
                oos = new ObjectOutputStream(Csocket.getOutputStream());
                while(true)
                {
                    Requete requete = (Requete) ois.readObject();
                    Reponse reponse = protocole.TraiteRequete(requete,Csocket);
                    oos.writeObject(reponse);
                }
            }
            catch (FinConnexionException ex)
            {
                logger.Trace("Fin de connexion demandé par le protocole");
                if(oos!=null && ex.getReponse() !=null)
                {
                    oos.writeObject(ex.getReponse());
                }
            }
        }
        catch (IOException ex){ logger.Trace("Erreur I/O");}
        catch (ClassNotFoundException ex) {logger.Trace("erreur requete invalide");}
        finally
        {
            try
            {
                Csocket.close();
            }
            catch (IOException ex )
            {
                logger.Trace("Erreur de fermeture de socket");
            }
        }
    }
}
