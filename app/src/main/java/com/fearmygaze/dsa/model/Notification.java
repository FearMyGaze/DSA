package com.fearmygaze.dsa.model;

public class Notification {
    private final int id; //Leave it
    private final int userID;//Leave it
    private final String docName;
    private final String fileTitle;
    private final String date;

    /**
     * @param id This is the id of the listing
     * @param userID This references the id of the that the doctor wants to access a certain file
     * @param docName This references the doctors name that made the request to access the file
     * @param fileTitle This references the title of specific file the doctor requested
     * @param date This references the date that the request was made
     */
    public Notification(int id, int userID, String docName, String fileTitle, String date) {
        this.id = id;
        this.userID = userID;
        this.docName = docName;
        this.fileTitle = fileTitle;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getDocName() {
        return docName;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public String getDate() {
        return date;
    }

}
