package edu.ufp.inf.sd.rmi.observer.server;

import java.io.Serializable;

/**
 * @author rui
 */
public class State implements Serializable {
    private String msg;
    private String id;

    /**
     * @param id
     * @param m
     */
    public State(String id, String m) {
        this.id = id;
        this.msg = m;
    }
    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @return
     */
    public String getInfo() {
        return this.msg;
    }

    /**
     * @param m
     */
    public void setInfo(String m) {
        this.msg = m;
    }
}
