package com.fearmygaze.dsa.model;

import androidx.annotation.NonNull;

public class File {

    private final int id;
    private final String title;
    private final String description;
    private final String date;

    public File(int id, String title, String description, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public String getDate() {
        return date;
    }

    @NonNull
    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
