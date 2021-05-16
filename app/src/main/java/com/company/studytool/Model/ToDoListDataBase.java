package com.company.studytool.Model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.company.studytool.ViewModel.ToDoListDao;

@Database(entities = {ToDoList.class}, version = 2)
public abstract class ToDoListDataBase extends RoomDatabase {
    private static ToDoListDataBase instance;

    public abstract ToDoListDao nodeDao();

    public static synchronized ToDoListDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ToDoListDataBase.class, "ToDoList_DataBase").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };
}
