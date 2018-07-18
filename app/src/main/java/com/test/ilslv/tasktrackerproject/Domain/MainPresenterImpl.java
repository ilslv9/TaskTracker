package com.test.ilslv.tasktrackerproject.Domain;

import com.test.ilslv.tasktrackerproject.Infrastructure.TaskRepository;

import java.util.List;

import javax.inject.Inject;

public class MainPresenterImpl implements MainContract.MainPresenter {

    MainContract.MainView mainView;
    TaskRepository taskRepository;

    @Inject
    public MainPresenterImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void onCreate(MainContract.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void loadTasks() {
        mainView.showTasks(taskRepository.getTasks());
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }
}
