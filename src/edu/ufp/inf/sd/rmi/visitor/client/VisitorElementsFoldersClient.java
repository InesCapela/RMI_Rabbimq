package edu.ufp.inf.sd.rmi.visitor.client;
import edu.ufp.inf.sd.rmi.util.rmisetup.SetupContextRMI;
import edu.ufp.inf.sd.rmi.visitor.server.ElementFolderRI;
import edu.ufp.inf.sd.rmi.visitor.server.VisitorFoldersOperationCreateFile;
import edu.ufp.inf.sd.rmi.visitor.server.VisitorFoldersOperationDeleteFile;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisitorElementsFoldersClient {

    private SetupContextRMI contextRMI;

    private ElementFolderRI efRI;

    public static void main(String[] args) {
        if (args != null && args.length < 2) {
            System.err.println("usage: java [options] edu.ufp.sd.inf.rmi.helloworld.server.HelloWorldClient <rmi_registry_ip> <rmi_registry_port> <service_name>");
            System.exit(-1);
        } else {
            //1. ============ Setup client RMI context ============
            VisitorElementsFoldersClient hwc = new VisitorElementsFoldersClient(args);
            //2. ============ Lookup service ============
            hwc.lookupService();
            //3. ============ Play with service ============
            hwc.playService();
        }
    }

    public VisitorElementsFoldersClient(String args[]) {
        try {
            //List ans set args
            SetupContextRMI.printArgs(this.getClass().getName(), args);
            String registryIP = args[0];
            String registryPort = args[1];
            String serviceName = args[2];
            //Create a context for RMI setup
            contextRMI = new SetupContextRMI(this.getClass(), registryIP, registryPort, new String[]{serviceName});
        } catch (RemoteException e) {
            Logger.getLogger(VisitorElementsFoldersClient.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private Remote lookupService() {
        try {
            //Get proxy to rmiregistry
            Registry registry = contextRMI.getRegistry();
            //Lookup service on rmiregistry and wait for calls
            if (registry != null) {
                //Get service url (including servicename)
                String serviceUrl = contextRMI.getServicesUrl(0);
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "going to lookup service @ {0}", serviceUrl);

                //============ Get proxy to HelloWorld service ============
                efRI = (ElementFolderRI) registry.lookup(serviceUrl);
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "registry not bound (check IPs). :(");
                //registry = LocateRegistry.createRegistry(1099);
            }
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return efRI;
    }

    private void playService() {
        try {
            //============ Call HelloWorld remote service ============
           // VisitorFoldersOperationCreateFile v = new VisitorFoldersOperationCreateFile("Hello");
            VisitorFoldersOperationDeleteFile v= new VisitorFoldersOperationDeleteFile("visitor Hello");
            this.efRI.acceptVisitor(v);

            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "going to finish, bye. ;)");
        } catch (RemoteException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
}
