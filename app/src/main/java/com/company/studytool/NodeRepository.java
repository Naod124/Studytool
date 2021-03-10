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

        nodeDao.insert(node);
    }

    public void updateTask(Node node) {
        nodeDao.update(node);
    }

    public void deleteTask(Node node) {
        nodeDao.delete(node);
    }

    public void deleteAllTask() {
        nodeDao.deleteAll();
    }


}
