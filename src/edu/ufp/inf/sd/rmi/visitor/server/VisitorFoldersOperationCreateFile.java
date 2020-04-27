package edu.ufp.inf.sd.rmi.visitor.server;

import java.io.Serializable;

//ou na visitor folders extends serializable
public class VisitorFoldersOperationCreateFile implements VisitorFoldersOperationsI, Serializable {

    private  String flieToCreate;
    private  String flieToCreateWithPrefix;

    public VisitorFoldersOperationCreateFile(String newFolder) {
        this.flieToCreate = newFolder;
        flieToCreateWithPrefix="visitorbook "+ flieToCreate;
    }

    @Override
    public Object visitConcreteElementsStateBooks(ElementFolderRI element) {
        SingletonFolderOperationsBooks s=((ConcreteElementeFolderBooksIMPL)element).getStateBooksFolder();
        //flieToCreateWithPrefix="visitorbook "+ flieToCreate;
        System.out.println("VisitorStateFolderOperationCreateFile- VisitConcreteElementsStateBookd(): going create");
        return s.createFile(flieToCreateWithPrefix);
    }

    @Override
    public Object visitConcreteElementsStateMagazines(ElementFolderRI element) {
        SingletonFolderOperationsMagazines s=((ConcreteElementeFolderMagazinesImpl)element).getStateMagazineFolder();
        //flieToCreateWithPrefix="visitorbook "+ flieToCreate;
        System.out.println("VisitorStateFolderOperationCreateFile- VisitConcreteElementsStateBookd(): going create");
        return s.createFile(flieToCreateWithPrefix);
    }
}
