package edu.ufp.inf.sd.rmi.pingpong.server;

import java.io.Serializable;

public class Ball implements Serializable {
    public int id;

    public Ball(int id) {
        this.id = id;
    }
}