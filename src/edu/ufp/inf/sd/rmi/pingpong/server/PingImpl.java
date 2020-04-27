package edu.ufp.inf.sd.rmi.pingpong.server;

import edu.ufp.inf.sd.rmi.pingpong.client.PongRI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PingImpl extends UnicastRemoteObject implements PingRI {

    public PingImpl() throws RemoteException {
        super();
    }
    @Override
    public void ping(Ball b, PongRI p) throws RemoteException {
        System.out.println("ping(): "+b.id);
        //p.pong(b);
        PingThread t = new PingThread(p, b);
        t.start();
    }
}
