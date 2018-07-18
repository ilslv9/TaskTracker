package com.test.ilslv.tasktrackerproject.Domain;

import com.test.ilslv.tasktrackerproject.Infrastructure.TaskRepository;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

public class TaskCreatePresenterImpl implements TaskCreateContract.TaskCreatePresenter {

    TaskCreateContract.TaskCreateView taskCreateEditView;
    TaskRepository taskRepository;

    @Inject
    public TaskCreatePresenterImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void onCreate(TaskCreateContract.TaskCreateView taskCreateEditView) {
        this.taskCreateEditView = taskCreateEditView;
    }

    @Override
    public void createTask(String taskTitle,
                           Date taskDate,
                           TaskStatus taskStatus,
                           String taskDescription) {
        if (taskTitle.isEmpty() || taskDescription.isEmpty()) {
            taskCreateEditView.showError();
        } else {
            taskRepository.addNewTask(new Task(UUID.randomUUID(),
                    taskTitle,
                    taskDate,
                    taskStatus,
                    taskDescription));
            taskCreateEditView.showResult();
        }
    }

    @Override
    public void onDestroy() {
        taskCreateEditView = null;
    }
}
