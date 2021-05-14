package com.example.noteapp.model;

import java.io.Serializable;

public class File implements Serializable {

        private int ID;
        private String title;
        private String body;

    public File(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public File(int id,String title, String body) {
        this.ID = id;
        this.title = title;
        this.body = body;
    }


    public File()
    {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}



