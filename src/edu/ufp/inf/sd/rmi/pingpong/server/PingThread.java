package edu.ufp.inf.sd.rmi.pingpong.server;

import edu.ufp.inf.sd.rmi.pingpong.client.PongRI;

import java.rmi.RemoteException;
import java.util.Random;

public class PingThread extends Thread {
    PongRI pongRI;
    Ball ball;

    public PingThread(PongRI pongRI, Ball ball) {
        this.pongRI = pongRI;
        this.ball = ball;
    }

    public void run(){
        try {
            Random r = new Random();
            int dropball = r.nextInt(100)+1;
            System.out.println("dropball = "+dropball);
            if (dropball>20) {
                int delay = r.nextInt(2000);
                Thread.currentThread().sleep(delay);
                this.pongRI.pong(ball);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
