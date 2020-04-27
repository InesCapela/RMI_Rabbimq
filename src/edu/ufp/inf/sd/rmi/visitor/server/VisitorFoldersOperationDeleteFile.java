package edu.ufp.inf.sd.rmi.visitor.server;
import java.io.Serializable;

public class VisitorFoldersOperationDeleteFile implements VisitorFoldersOperationsI, Serializable {

    private String fileToDelete;
    private String fileToDeletePrefix;

    public VisitorFoldersOperationDeleteFile(String newFolder){
        this.fileToDelete=newFolder;
    }

    @Override
    public Object visitConcreteElementsStateBooks(ElementFolderRI element) {
        SingletonFolderOperationsBooks s=((ConcreteElementeFolderBooksIMPL)element).getStateBooksFolder();
        fileToDeletePrefix="VisitorBooj"+fileToDelete;
        System.out.println("visitorFolderOperationDeleteFile- VisitConcreteElementsStateBooks(): going dekere" );
        return  s.deleteFile(fileToDeletePrefix);
    }

    @Override
    public Object visitConcreteElementsStateMagazines(ElementFolderRI element) {
        SingletonFolderOperationsMagazines s=((ConcreteElementeFolderMagazinesImpl)element).getStateMagazineFolder();
        fileToDeletePrefix="VisitorBooj"+fileToDelete;
        System.out.println("visitorFolderOperationDeleteFile- VisitConcreteElementsStateBooks(): going dekere" );
        return  s.deleteFile(fileToDeletePrefix);
    }

    public String getFileToDelete() {
        return fileToDelete;
    }

    public void setFileToDelete(String fileToDelete) {
        this.fileToDelete = fileToDelete;
    }
}
