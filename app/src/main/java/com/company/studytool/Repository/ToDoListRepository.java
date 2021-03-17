package com.company.studytool.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.company.studytool.Model.ToDoList;
import com.company.studytool.ViewModel.ToDoListDao;
import com.company.studytool.Model.ToDoListDataBase;

import java.util.List;


public class ToDoListRepository {
    private ToDoListDao toDoListDao;
    private LiveData<List<ToDoList>> listLiveData;


    public ToDoListRepository(Application application) {
        ToDoListDataBase toDoListDataBase = ToDoListDataBase.getInstance(application);
        toDoListDao = toDoListDataBase.nodeDao();
        listLiveData = toDoListDao.getAllNodes();
    }

    public void insert(ToDoList toDoList) {
        insertTask(toDoList);
    }

    public void update(ToDoList toDoList) {
        updateTask(toDoList);
    }

    public void delete(ToDoList toDoList) {
        deleteTask(toDoList);
    }

    public void deleteAll() {
        deleteAllTask();
    }


    public LiveData<List<ToDoList>> getListLiveData() {
        return listLiveData;
    }

    // insert task starts ...
    public void insertTask(ToDoList toDoList) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                toDoListDao.insert(toDoList);
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

    public void updateTask(ToDoList toDoList) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                toDoListDao.update(toDoList);
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

    public void deleteTask(ToDoList toDoList) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                toDoListDao.delete(toDoList);
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

    public void deleteAllTask() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                toDoListDao.deleteAll();
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }


}
