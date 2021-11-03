package com.example.taskmaster;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {TaskClass.class}, version = 1)

public abstract class TasksDatabase extends RoomDatabase {

    public abstract TasksDAO tasksDAO();


    private static TasksDatabase tasksDatabase;

    public TasksDatabase() {

    }

    public static synchronized TasksDatabase getInstance(Context context) {

        if(tasksDatabase == null){
            tasksDatabase = Room.databaseBuilder(context,
                    TasksDatabase.class, "TasksDatabase").allowMainThreadQueries().build();
        }

       return tasksDatabase;
    }


}