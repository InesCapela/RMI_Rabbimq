package edu.ufp.inf.sd.rmi.calculate.server;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
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
public class CalculatorImpl extends UnicastRemoteObject implements CalculatorRI {

    // Uses RMI-default sockets-based transport
    // Runs forever (do not passivates) - Do not needs rmid (activation deamon)
    // Constructor must throw RemoteException due to export()
    public CalculatorImpl() throws RemoteException {
        // Invokes UnicastRemoteObject constructor which exports remote object
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int addA(ArrayList<Float> add) throws RemoteException {
        int result = 0;
        for (int i = 0; i < add.size(); i++) {

        }
        return result;
    }

    @Override
    public int sub(int a, int b) throws RemoteException {
        return a - b;
    }

    @Override
    public int mult(int a, int b) throws RemoteException {
        return a * b;
    }

    @Override
    public int div(int a, int b) throws RemoteException {
        return a / b;
    }
}
