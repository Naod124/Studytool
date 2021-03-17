package com.company.studytool.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.company.studytool.Model.ToDoList;

import java.util.List;

@Dao
public interface ToDoListDao {
    @Insert
    void insert(ToDoList toDoList);

    @Delete
    void delete(ToDoList toDoList);

    @Update
    void update(ToDoList toDoList);

    @Query("Delete from favorite_table")
    void deleteAll();

    @Query("select * from favorite_table order by priority DESC")
    LiveData<List<ToDoList>> getAllNodes();


}
