package edu.ufp.inf.sd.rmi.observer.server;

import edu.ufp.inf.sd.rmi.observer.client.ObserverRI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SubjectRI extends Remote {
    public void attach(ObserverRI observerRI) throws RemoteException;
    public void detach(ObserverRI observerRI) throws RemoteException;
    public State getState() throws RemoteException;
    public void setState(State state) throws RemoteException;

}
