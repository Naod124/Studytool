package com.company.studytool;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NodeDao {
    @Insert
    void insert(Node node);

    @Delete
    void delete(Node node);

    @Update
    void update(Node node);

    @Query("Delete from favorite_table")
    void deleteAll();

    @Query("select * from favorite_table order by priority DESC")
    LiveData<List<Node>> getAllNodes();

}
