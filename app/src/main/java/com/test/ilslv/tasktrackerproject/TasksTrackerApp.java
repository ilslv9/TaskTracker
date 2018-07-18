package com.test.ilslv.tasktrackerproject;

import android.app.Application;

import com.test.ilslv.tasktrackerproject.DI.DaggerTasksTrackerComponent;
import com.test.ilslv.tasktrackerproject.DI.TasksTrackerComponent;

public class TasksTrackerApp extends Application {

   private static TasksTrackerComponent tasksTrackerComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        tasksTrackerComponent = DaggerTasksTrackerComponent.builder().build();
    }

    public static TasksTrackerComponent getTasksTrackerComponent() {
        return tasksTrackerComponent;
    }
}
