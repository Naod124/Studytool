package com.company.studytool;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_table")
public class Node {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(defaultValue = "Title")
    private String title;
    @ColumnInfo(defaultValue = "Description")
    private String description;
    @ColumnInfo(defaultValue = "Priority")
    private int priority;

    public Node(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
