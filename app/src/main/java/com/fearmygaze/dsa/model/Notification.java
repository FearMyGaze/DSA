package com.fearmygaze.dsa.model;

public class Notification {
    private final int id;
    private final int userID;
    private final int docsID;
    private final int fileID;

    public Notification(int id, int userID, int docsID, int fileID) {
        this.id = id;
        this.userID = userID;
        this.docsID = docsID;
        this.fileID = fileID;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public int getDocsID() {
        return docsID;
    }

    public int getFileID() {
        return fileID;
    }
}
