package com.test.ilslv.tasktrackerproject.Domain;

import java.util.Date;
import java.util.UUID;

public interface TaskEditContract {
    interface TaskEditView{
        void showTaskData(Task task);
        void showResult();
        void showError();
    }
    interface TaskEditPresenter{
        void onCreate(TaskEditView taskEditView);
        void editTask(UUID taskId, String taskTitle, Date taskDate, TaskStatus taskStatus, String taskDescription);
        void getNoEditTaskData(UUID taskId);
        void onDestroy();
    }
}
