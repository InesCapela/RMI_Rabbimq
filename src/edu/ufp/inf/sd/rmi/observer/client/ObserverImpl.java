package edu.ufp.inf.sd.rmi.observer.client;

import edu.ufp.inf.sd.rmi.observer.server.State;
import edu.ufp.inf.sd.rmi.observer.server.SubjectRI;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObserverImpl extends UnicastRemoteObject implements ObserverRI {

    private String id;
    private State lastObserverState;
    public SubjectRI subjectRI;
    protected ObserverGuiClient chatFrame;

    public ObserverImpl(String id, State lastObserverState, SubjectRI subjectRI, ObserverGuiClient chatFrame) throws RemoteException {
        this.id = id;
        this.lastObserverState = new State(id, "");
        this.chatFrame = chatFrame;
    }

    public ObserverImpl(int port, String id, State lastObserverState, SubjectRI subjectRI, ObserverGuiClient chatFrame) throws RemoteException {
        super(port);
        this.id = id;
        this.lastObserverState = lastObserverState;
        this.subjectRI = subjectRI;
        this.chatFrame = chatFrame;
    }

    public ObserverImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf, String id, State lastObserverState, SubjectRI subjectRI, ObserverGuiClient chatFrame) throws RemoteException {
        super(port, csf, ssf);
        this.id = id;
        this.lastObserverState = lastObserverState;
        this.subjectRI = subjectRI;
        this.chatFrame = chatFrame;
        this.subjectRI.attach(this);
    }

    public ObserverImpl(String username, ObserverGuiClient observerGuiClient, SubjectRI subjectRI) throws RemoteException {
        super();
        this.id = id;
        this.subjectRI = subjectRI;
        this.chatFrame = observerGuiClient;
        this.subjectRI.attach(this);
    }


    @Override
    public void update() throws RemoteException {
        this.lastObserverState = subjectRI.getState();
        this.chatFrame.updateTextArea();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public State getLastObserverState() {
        return lastObserverState;
    }

    public void setLastObserverState(State lastObserverState) {
        this.lastObserverState = lastObserverState;
    }

    public SubjectRI getSubjectRI() {
        return subjectRI;
    }

    public void setSubjectRI(SubjectRI subjectRI) {
        this.subjectRI = subjectRI;
    }

    public ObserverGuiClient getChatFrame() {
        return chatFrame;
    }

    public void setChatFrame(ObserverGuiClient chatFrame) {
        this.chatFrame = chatFrame;
    }
}
