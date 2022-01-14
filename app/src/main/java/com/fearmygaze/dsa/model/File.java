package com.fearmygaze.dsa.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class File {

    private final int id;
    private final String title;
    private final String description;
    private final String data;
    private final Date date;

    public File(int id, String title, String description, String data, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.data = data;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getData() {
        return data;
    }

    public Date getDate() {
        return date;
    }

    @NonNull
    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", data='" + data + '\'' +
                ", date=" + date +
                '}';
    }
}
