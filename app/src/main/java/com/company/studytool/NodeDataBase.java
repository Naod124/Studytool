package com.company.studytool;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Node.class}, version = 2)
public abstract class NodeDataBase extends RoomDatabase {
    private static NodeDataBase instance;

    public abstract NodeDao nodeDao();

    public static synchronized NodeDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NodeDataBase.class, "Node_DataBase").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };
    private void initializeDb (){

    }
}
