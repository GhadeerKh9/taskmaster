package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao

public interface TasksDAO {



    @Query("SELECT * FROM taskClass")
    List<TaskClass> getAll();

    @Query("SELECT * FROM taskClass WHERE id = :id")
    TaskClass getTaskClassByID(Long id);



    @Insert
    Long insertItem(TaskClass oneTask);



}
