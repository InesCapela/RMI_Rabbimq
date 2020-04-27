package edu.ufp.inf.sd.rmi.calculate.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CalculatorRI extends Remote {


    public int add(int a, int b) throws RemoteException;
    public int addA(ArrayList<Float>add) throws RemoteException; //RemoteArithmeticException
    public int sub(int a, int b) throws RemoteException;
    public int mult(int a, int b) throws RemoteException;
    public int div(int a, int b) throws RemoteException;

}
