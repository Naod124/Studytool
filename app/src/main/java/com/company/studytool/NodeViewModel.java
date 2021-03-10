package com.company.studytool;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NodeViewModel extends AndroidViewModel {
    private LiveData<List<Node>> listLiveData;
    private NodeRepository repository;

    public NodeViewModel(@NonNull Application application) {
        super(application);
        repository = new NodeRepository(application);
        listLiveData = repository.getListLiveData();
    }

    public void insert(Node node) {
        repository.insert(node);
    }

    public void update(Node node) {
        repository.update(node);
    }

    public void delete(Node node) {
        repository.delete(node);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<Node>> getListLiveData() {
        return listLiveData;
    }
}
