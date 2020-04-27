package edu.ufp.inf.sd.rmi.diglib.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DigLibFactoryRI extends Remote {

    public DigLibSessionRI login(String uname, String pw) throws RemoteException; //Aqui estamos a implementar o factory
    public boolean register(String uname, String pw) throws RemoteException;

}
