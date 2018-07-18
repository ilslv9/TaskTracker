package com.test.ilslv.tasktrackerproject.Domain;

import java.util.Date;
import java.util.UUID;

public interface TaskCreateContract {
    interface TaskCreateView{
        void showResult();
    }
    interface TaskCreatePresenter{
        void onCreate(TaskCreateView taskCreateView);
        void createTask(String taskTitle, Date taskDate, TaskStatus taskStatus, String taskDescription);
        void onDestroy();
    }
}
