package com.test.ilslv.tasktrackerproject.Infrastructure;

import com.test.ilslv.tasktrackerproject.Domain.Task;
import com.test.ilslv.tasktrackerproject.Domain.TaskStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

public class TaskRealmRepository implements TaskRepository {

    Realm realm;

    @Inject
    public TaskRealmRepository(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Task getTaskById(UUID taskId) {
        Task task = realm.where(Task.class).equalTo("taskId", taskId.toString()).findFirst();
        return task;
    }

    @Override
    public void addNewTask(Task task) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(task);
        realm.commitTransaction();
    }

    @Override
    public void editTask(UUID taskId, String editedTitle, Date editedDate, TaskStatus editedTaskStatus, String editedTaskDescription) {
        Task editedTask = null;
        List<Task> tasks = realm.where(Task.class).findAll();
        for (Task task :
                tasks) {
            if (task.getTaskId().equals(taskId)) {
                editedTask = task;
                break;
            }
        }
        if (editedTask == null)
            throw new IllegalStateException("Task with such id does not exist");
        else {
            realm.beginTransaction();
            editedTask.editTask(editedTitle, editedDate, editedTaskStatus, editedTaskDescription);
            realm.copyToRealmOrUpdate(editedTask);
            realm.commitTransaction();
        }
    }

    @Override
    public void deleteTask(UUID taskId) {
        Task editedTask = null;
        List<Task> tasks = realm.where(Task.class).findAll();
        for (Task task :
                tasks) {
            if (task.getTaskId().equals(taskId)) {
                editedTask = task;
                break;
            }
        }
        if (editedTask == null)
            throw new IllegalStateException("Task with such id does not exist");
        else {
            realm.beginTransaction();
            Task results = realm.where(Task.class).equalTo("taskId", taskId.toString()).findFirst();
            results.deleteFromRealm();
            realm.commitTransaction();
        }
    }

    @Override
    public List<Task> filterTaskByStatus(TaskStatus taskStatus) {
        return realm.where(Task.class).equalTo("taskStatus", taskStatus.toString()).findAll();
    }

    @Override
    public List<Task> getTasks() {
        return realm.where(Task.class).findAll();
    }
}
