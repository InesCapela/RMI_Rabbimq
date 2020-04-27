package edu.ufp.inf.sd.rmi.pingpong.client;

import edu.ufp.inf.sd.rmi.pingpong.server.Ball;
import edu.ufp.inf.sd.rmi.pingpong.server.PingRI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class PongImpl extends UnicastRemoteObject implements PongRI{

    public PingRI pingRI;
    public PongImpl(PingRI pingRI) throws RemoteException {
        super();
        this.pingRI = pingRI;
    }
    @Override
    public void pong(Ball b) throws RemoteException {
        System.out.println("pong(): Receive ball "+b.id);
        //this.pingRI.ping(b,this);
        Random r = new Random();
        int dropball = r.nextInt(100) + 1;
        System.out.println("dropball = "+dropball);
        if (dropball>20) {
            int delay = r.nextInt(2000);
            try {
                Thread.currentThread().sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.pingRI.ping(b, this);
        }
    }

    public void startPlaying(Ball b) throws RemoteException {
        System.out.println("startPlaying(): with ball "+b.id);
        this.pingRI.ping(b,this);
    }
}
