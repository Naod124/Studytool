package com.company.studytool;

import android.app.Application;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import androidx.lifecycle.LiveData;

import java.util.List;


public class NodeRepository {
    private NodeDao nodeDao;
    private LiveData<List<Node>> listLiveData;


    public NodeRepository(Application application) {
        NodeDataBase nodeDataBase = NodeDataBase.getInstance(application);
        nodeDao = nodeDataBase.nodeDao();
        listLiveData = nodeDao.getAllNodes();
    }

    public void insert(Node node) {
        insertTask(node);
    }

    public void update(Node node) {
        updateTask(node);
    }

    public void delete(Node node) {
        deleteTask(node);
    }

    public void deleteAll() {
        deleteAllTask();
    }


    public LiveData<List<Node>> getListLiveData() {
        return listLiveData;
    }

    // insert task starts ...
    public void insertTask(Node node) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                nodeDao.insert(node);
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

    public void updateTask(Node node) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                nodeDao.update(node);
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

    public void deleteTask(Node node) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                nodeDao.delete(node);
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

    public void deleteAllTask() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                nodeDao.deleteAll();
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }


}
