package com.test.ilslv.tasktrackerproject.Domain;

import java.util.List;

public interface MainContract {

    interface MainView {
        void showTasks(List<Task> tasks);
    }

    interface MainPresenter {
        void onCreate(MainView mainView);
        void loadTasks();
        void filterTasks(TaskStatus taskStatus);
        void onDestroy();
    }

}
