package com.example.taskmaster;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class TaskClass {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name="taskTitle")
    public String title;

    @ColumnInfo(name="taskBody")
    public String body;


    @ColumnInfo(name="taskState")
    public String state;


    public TaskClass(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
