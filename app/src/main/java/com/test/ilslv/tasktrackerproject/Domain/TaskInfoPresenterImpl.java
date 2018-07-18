package com.test.ilslv.tasktrackerproject.Domain;

import com.test.ilslv.tasktrackerproject.Infrastructure.TaskRepository;

import java.util.UUID;

import javax.inject.Inject;

public class TaskInfoPresenterImpl implements TaskInfoContract.TaskInfoPresenter {

    TaskInfoContract.TaskInfoView taskInfoView;
    TaskRepository taskRepository;

    @Inject
    public TaskInfoPresenterImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void onCreate(TaskInfoContract.TaskInfoView taskInfoView) {
        this.taskInfoView = taskInfoView;
    }

    @Override
    public void loadTaskInfo(UUID taskId) {
        taskInfoView.showTaskInfo(taskRepository.getTaskById(taskId));
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.deleteTask(taskId);
        taskInfoView.showDeleteResult();
    }

    @Override
    public void onDestroy() {
        taskInfoView = null;
    }
}
