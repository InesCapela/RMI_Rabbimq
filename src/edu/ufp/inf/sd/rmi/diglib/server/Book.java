package edu.ufp.inf.sd.rmi.diglib.server;

import java.io.Serializable;

//Serializable em objetos que transitem entre rede!! IMPORTANTE
public class Book implements Serializable {

    private String author = "";
    private String title = "";
    private Boolean requested= false;

    public Book(String t, String a) {
        author = a;
        title = t;
    }

    @Override
    public String toString() {
        return "Book{" + "author=" + getAuthor() + ", title=" + getTitle() + '}';
    }


    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}