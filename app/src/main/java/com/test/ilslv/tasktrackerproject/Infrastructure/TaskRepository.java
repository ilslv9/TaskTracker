package com.test.ilslv.tasktrackerproject.Infrastructure;

import com.test.ilslv.tasktrackerproject.Domain.Task;
import com.test.ilslv.tasktrackerproject.Domain.TaskStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TaskRepository {

    Task getTaskById(UUID taskId);
    void addNewTask(Task task);
    void editTask(UUID taskId,
                  String editedTitle,
                  Date editedDate,
                  TaskStatus editedTaskStatus,
                  String editedTaskDescription);
    void deleteTask(UUID taskId);
    List<Task> filterTaskByStatus(TaskStatus taskStatus);
    List<Task> getTasks();

}
