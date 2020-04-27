package edu.ufp.inf.sd.rmi.observer.server;

import edu.ufp.inf.sd.rmi.observer.client.ObserverRI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


/**
 * <p>Title: Projecto SD</p>
 * <p>Description: Projecto apoio aulas SD</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: UFP </p>
 *
 * @author Rui S. Moreira
 * @version 3.0
 */
public class SubjectImpl extends UnicastRemoteObject implements SubjectRI {

    private State subjectState;
    private final ArrayList<ObserverRI> observers = new ArrayList<>();

    // Uses RMI-default sockets-based transport
    // Runs forever (do not passivates) - Do not needs rmid (activation deamon)
    // Constructor must throw RemoteException due to export()
    public SubjectImpl(State subjectState) throws RemoteException {
        super();
        this.subjectState = subjectState;
    }

    public SubjectImpl() throws RemoteException {
        super();
        this.subjectState = new State("", "");
    }

    /**
     * outra opçao sem ser o extend
     *
     * @throws RemoteException
     */
    /*private void exportObserverProxyAndAttachToSubject() throws RemoteException{
        UnicastRemoteObject.exportObject(this,0);

    }*/
    public void notifyAllObservers() throws RemoteException {
        for (ObserverRI obs : observers) {
            obs.update();
        }
    }

/*    @Override
    public void print(String msg) throws RemoteException {
        //System.out.println("HelloWorldImpl - print(): someone called me with msg = "+ msg);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "someone called me with msg = {0}", new Object[]{msg});
    }*/

    @Override
    public void attach(ObserverRI observerRI) throws RemoteException {
        if (!observers.contains((observerRI))) {
            observers.add(observerRI);
        }
    }

    @Override
    public void detach(ObserverRI observerRI) throws RemoteException {
        observers.remove(observerRI);
    }

    @Override
    public State getState() throws RemoteException {
        return subjectState;
    }

    @Override
    public void setState(State state) throws RemoteException {
        this.subjectState = state;
        this.notifyAllObservers();
    }
}
