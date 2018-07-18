package com.test.ilslv.tasktrackerproject.Domain;

import java.util.UUID;

public interface TaskInfoContract {
    interface TaskInfoView{
        void showTaskInfo(Task task);
        void showDeleteResult();
    }

    interface TaskInfoPresenter{
        void onCreate(TaskInfoView taskInfoView);
        void loadTaskInfo(UUID taskId);
        void deleteTask(UUID taskId);
        void onDestroy();
    }
}
