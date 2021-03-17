package com.company.studytool.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.company.studytool.Model.ToDoList;
import com.company.studytool.Repository.ToDoListRepository;

import java.util.List;

public class ToDoListViewModel extends AndroidViewModel {
    private LiveData<List<ToDoList>> listLiveData;
    private ToDoListRepository repository;

    public ToDoListViewModel(@NonNull Application application) {
        super(application);
        repository = new ToDoListRepository(application);
        listLiveData = repository.getListLiveData();
    }

    public void insert(ToDoList toDoList) {
        repository.insert(toDoList);
    }

    public void update(ToDoList toDoList) {
        repository.update(toDoList);
    }

    public void delete(ToDoList toDoList) {
        repository.delete(toDoList);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<ToDoList>> getListLiveData() {
        return listLiveData;
    }
}
