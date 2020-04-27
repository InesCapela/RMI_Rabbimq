package edu.ufp.inf.sd.rmi.visitor.server;

public class ConcreteElementeFolderMagazinesImpl {
    private SingletonFolderOperationsMagazines stateMagazineFolder;

    public SingletonFolderOperationsMagazines getStateMagazineFolder() {
        return stateMagazineFolder;
    }

    public void setStateMagazineFolder(SingletonFolderOperationsMagazines stateMagazineFolder) {
        this.stateMagazineFolder = stateMagazineFolder;
    }
}
