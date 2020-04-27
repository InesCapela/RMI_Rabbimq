package edu.ufp.inf.sd.rmi.diglib.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DigLibFactoryImpl  extends UnicastRemoteObject implements DigLibFactoryRI {

    private DBMockup db;// = new DBMockup();
    //private ThreadPool pool;// = new ThreadPool(10);
    private HashMap<String, DigLibSessionRI> sessions;// = new HashMap();

    /**
     * Uses RMI-default sockets-based transport. Runs forever (do not
     * passivates) hence, does not need rmid (activation deamon) Constructor
     * must throw RemoteException due to super() export().
     */
    public DigLibFactoryImpl() throws RemoteException {
        // Invokes UnicastRemoteObject constructor which exports remote object
        super();
        db = new DBMockup();
        //pool = new ThreadPool(10);
        sessions = new HashMap();
    }

    @Override
    public DigLibSessionRI login(String uname, String pw) throws RemoteException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Login= " + uname + "," + pw);

        if (db.exists(uname, pw)) {
            if (!sessions.containsKey(uname)) {
                DigLibSessionRI digLibSessionRI = new DigLibSessionImpl(this,new User(uname, pw),db);
               /* não testa se já existe cria         */
               /* DigLibSessionRI sessionRI;
               sessionRI = new DigLibSessionImpl(this, new User(uname, pw),db);
                this.sessions.put(uname, sessionRI);*/
                return digLibSessionRI;
            } else {
                //se o user exite
                return sessions.get(uname);
            }
        }
        return null;
    }

    @Override
    public boolean register(String uname, String pw) throws RemoteException {
        if(db.exists(uname,pw)){
            db.register(uname,pw);
            return true;
        }
        return false;
    }
    private DigLibSessionRI getDigLibSession(String username){
            DigLibSessionRI sessionRI= null;
            this.sessions.get(username);
            return sessionRI;
    }

    public DBMockup getDb() {
        return db;
    }

    public HashMap<String, DigLibSessionRI> getSessions() {
        return sessions;
    }

    public void setSessions(HashMap<String, DigLibSessionRI> sessions) {
        this.sessions = sessions;
    }
}
