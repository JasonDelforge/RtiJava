package Serveur;
import java.io.IOException;
public class ThreadClientPool extends ThreadClient
{
    private FIleAttente connexionsEnAttente;
    public ThreadClientPool(Protocole protocole,FIleAttente file , ThreadGroup groupe,Logger logger) throws IOException
    {
        super(protocole,groupe,logger);
        connexionsEnAttente = file;
    }
    @Override
    public void run()
    {
        logger.Trace("TH Client (Pool) demarre.....");
        boolean interrompu = false;
        while(!interrompu)
        {
            try
            {
                logger.Trace("Attente d'une connexion.....");
                Csocket = connexionsEnAttente.getConnexion();
                logger.Trace("Connexion prise en charge");
                super.run();
            }
            catch (InterruptedException ex)
            {
                logger.Trace("Demande d'interruption....");
                interrompu = true;
            }
        }
        logger.Trace("Th Client (Pool) se termine");
    }
}
