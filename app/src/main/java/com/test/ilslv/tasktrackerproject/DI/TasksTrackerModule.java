package com.test.ilslv.tasktrackerproject.DI;

import android.support.transition.Visibility;

import com.test.ilslv.tasktrackerproject.Domain.MainContract;
import com.test.ilslv.tasktrackerproject.Domain.MainPresenterImpl;
import com.test.ilslv.tasktrackerproject.Domain.TaskCreateContract;
import com.test.ilslv.tasktrackerproject.Domain.TaskCreatePresenterImpl;
import com.test.ilslv.tasktrackerproject.Domain.TaskEditContract;
import com.test.ilslv.tasktrackerproject.Domain.TaskEditPresenterImpl;
import com.test.ilslv.tasktrackerproject.Domain.TaskInfoContract;
import com.test.ilslv.tasktrackerproject.Domain.TaskInfoPresenterImpl;
import com.test.ilslv.tasktrackerproject.Infrastructure.TaskRepository;
import com.test.ilslv.tasktrackerproject.Infrastructure.TaskRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TasksTrackerModule {

    @Provides
    @Singleton
    public TaskRepository provideTaskRepository(){
        return new TaskRepositoryImpl();
    }

    @Provides
    public MainContract.MainPresenter provideMainPresenter(TaskRepository taskRepository){
        return new MainPresenterImpl(taskRepository);
    }

    @Provides
    public TaskCreateContract.TaskCreatePresenter provideTaskCreatePresenter(TaskRepository taskRepository){
        return new TaskCreatePresenterImpl(taskRepository);
    }

    @Provides
    public TaskInfoContract.TaskInfoPresenter provideTaskInfoPresenter(TaskRepository taskRepository){
        return new TaskInfoPresenterImpl(taskRepository);
    }

    @Provides
    public TaskEditContract.TaskEditPresenter provideTaskEditPresenter(TaskRepository taskRepository){
        return new TaskEditPresenterImpl(taskRepository);
    }

}
