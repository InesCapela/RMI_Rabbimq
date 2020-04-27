package edu.ufp.inf.sd.rmi.diglib.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DigLibSessionImpl implements DigLibSessionRI {

    private DBMockup db;
    protected DigLibFactoryImpl factory;
    private User user;

    public DigLibSessionImpl(DigLibFactoryImpl factory, User user, DBMockup db) throws RemoteException {
        super();
        this.db = db;
        this.factory = factory;
        this.user = user;
        this.exportDigLibSession();
    }

    private void exportDigLibSession() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
    }


    @Override
    public Book[] search(String title, String author) throws RemoteException {

        String thread = Thread.currentThread().getName();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "thread= " + thread + ": search (author= " + author);
        return this.factory.getDb().select(title, author);
    }


    @Override
    public void logout() throws RemoteException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "thread = " + Thread.currentThread().getName());
        this.removeDigLibSession(this.user.getUname(), this);
    }

    private void removeDigLibSession(String uname, DigLibSessionImpl digLibSession) {

        if (digLibSession.getClass().getName().compareToIgnoreCase(uname) == 0) {
            factory.getSessions().remove(uname);
        } else {
            System.out.println("Não existe uname");
        }
    }
}
