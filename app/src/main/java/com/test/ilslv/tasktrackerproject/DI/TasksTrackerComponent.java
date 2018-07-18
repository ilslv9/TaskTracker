package com.test.ilslv.tasktrackerproject.DI;

import com.test.ilslv.tasktrackerproject.UI.AddNewTaskActivity;
import com.test.ilslv.tasktrackerproject.UI.MainActivity;
import com.test.ilslv.tasktrackerproject.UI.TaskEditActivity;
import com.test.ilslv.tasktrackerproject.UI.TaskInfoActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = TasksTrackerModule.class)
@Singleton
public interface TasksTrackerComponent {
    void inject(MainActivity mainActivity);
    void inject(AddNewTaskActivity addNewTaskActivity);
    void inject(TaskInfoActivity taskInfoActivity);
    void inject(TaskEditActivity taskEditActivity);
}
