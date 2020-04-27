package edu.ufp.inf.sd.rmi.visitor.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConcreteElementeFolderBooksIMPL extends UnicastRemoteObject implements ElementFolderRI {

    private final SingletonFolderOperationsBooks stateBooksFolder;

    public ConcreteElementeFolderBooksIMPL(String booksFolder) throws RemoteException {
        super();
        //this.stateBooksFolder = stateBooksFolder;
        this.stateBooksFolder=SingletonFolderOperationsBooks.createSingletonFolderOperationsBooks(booksFolder);
    }

    @Override
    public Object acceptVisitor(VisitorFoldersOperationsI visitor) throws RemoteException {
        Object o= visitor.visitConcreteElementsStateBooks(this);
        return o;
    }

    public SingletonFolderOperationsBooks getStateBooksFolder() {
        return stateBooksFolder;
    }
}
