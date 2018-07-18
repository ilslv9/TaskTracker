package com.test.ilslv.tasktrackerproject.Domain;

import com.test.ilslv.tasktrackerproject.Infrastructure.TaskRepository;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

public class TaskEditPresenterImpl implements TaskEditContract.TaskEditPresenter {

    TaskEditContract.TaskEditView taskEditView;
    TaskRepository taskRepository;

    @Inject
    public TaskEditPresenterImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void onCreate(TaskEditContract.TaskEditView taskEditView) {
        this.taskEditView = taskEditView;
    }

    @Override
    public void editTask(UUID taskId, String taskTitle, Date taskDate, TaskStatus taskStatus, String taskDescription) {
        taskRepository.editTask(taskId, taskTitle, taskDate, taskStatus, taskDescription);
        taskEditView.showResult();
    }

    @Override
    public void getNoEditTaskData(UUID taskId) {
        taskEditView.showTaskData(taskRepository.getTaskById(taskId));
    }

    @Override
    public void onDestroy() {
        taskEditView = null;
    }
}
