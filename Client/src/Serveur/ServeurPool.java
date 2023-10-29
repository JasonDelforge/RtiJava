package Serveur;
import java.io.IOException;
import java.net.*;

public class ServeurPool extends ThreadServeur
{
    private FIleAttente  connexionEnAttente;
    private ThreadGroup pool;
    private int taillePool;
    protected Logger logger;
    public ServeurPool(int port, Protocole protocole, int taillePool, Logger logger) throws IOException
    {
        super(port,protocole,logger);
        connexionEnAttente = new FIleAttente();
        pool = new ThreadGroup("POOL");
        this.taillePool = taillePool;
    }
    @Override
    public void run()
    {
        logger.Trace("Demarrage du TH serveur pool.....");
        try
        {
            for(int i=0;i<taillePool;i++)
            {
                new ThreadClientPool(protocole,connexionEnAttente,pool,logger).start();
            }
        }
        catch (IOException ex)
        {
            logger.Trace("Erreur I/O lors de la creation  du pool  de threads");
            return;
        }
        while(!this.isInterrupted())
        {
            Socket Csocket;
            try
            {
                Ssocket.setSoTimeout(2000);
                Csocket = Ssocket.accept();
                logger.Trace("Connexion accepte, mise en file d'attente ");
                connexionEnAttente.addConnexion(Csocket);
            }
            catch (SocketTimeoutException ex)
            {
                //verifie si le thread a ete interrompu
            }
            catch (IOException ex)
            {
                logger.Trace("Erreur de I/O");
            }
        }
        logger.Trace("Th Serveur (Pool) interrompu");
        pool.interrupt();
    }
}
