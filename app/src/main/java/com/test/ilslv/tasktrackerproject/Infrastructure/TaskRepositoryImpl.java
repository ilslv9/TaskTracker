package com.test.ilslv.tasktrackerproject.Infrastructure;

import com.test.ilslv.tasktrackerproject.Domain.Task;
import com.test.ilslv.tasktrackerproject.Domain.TaskStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaskRepositoryImpl implements TaskRepository {

    List<Task> tasks;

    public TaskRepositoryImpl() {
        tasks = new ArrayList<>();
    }

    @Override
    public Task getTaskById(UUID taskId) {
        Task findedTask = null;
        for (Task task :
                tasks) {
            if (task.getTaskId().equals(taskId)) {
                findedTask = task;
                break;
            }
        }
        if (findedTask == null) throw new IllegalStateException("Task with such id does not exist");
        else
            return findedTask;
    }

    @Override
    public void addNewTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void editTask(UUID taskId, String editedTitle, Date editedDate, TaskStatus editedTaskStatus, String editedTaskDescription) {
        Task editedTask = null;
        for (Task task :
                tasks) {
            if (task.getTaskId().equals(taskId)) {
                editedTask = task;
                break;
            }
        }
        if (editedTask == null)
            throw new IllegalStateException("Task with such id does not exist");
        else
            editedTask.editTask(editedTitle, editedDate, editedTaskStatus, editedTaskDescription);
    }

    @Override
    public void deleteTask(UUID taskId) {
        Task deletedTask = null;
        for (Task task :
                tasks) {
            if (task.getTaskId().equals(taskId)) {
                deletedTask = task;
                break;
            }
        }
        if (deletedTask == null)
            throw new IllegalStateException("Task with such id does not exist");
        else tasks.remove(deletedTask);
    }

    @Override
    public List<Task> filterTaskByStatus(TaskStatus taskStatus) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task :
                tasks) {
            if (task.getTaskStatus().equals(taskStatus))
                filteredTasks.add(task);
        }
        return filteredTasks;
    }
}
