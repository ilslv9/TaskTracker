package com.test.ilslv.tasktrackerproject.Domain;

import java.util.Date;
import java.util.UUID;

public class Task {

    private UUID taskId;
    private String taskTitle;
    private Date taskDate;
    private TaskStatus taskStatus;
    private String taskDescription;

    public Task(UUID taskId, String taskTitle, Date taskDate, TaskStatus taskStatus, String taskDescription) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDate = taskDate;
        this.taskStatus = taskStatus;
        this.taskDescription = taskDescription;
    }

    public void editTask(String editedTitle,
                         Date editedDate,
                         TaskStatus editedTaskStatus,
                         String editedTaskDescription) {
        if (editedTitle != null)
            setTaskTitle(editedTitle);
        if (editedDate != null)
            setTaskDate(editedDate);
        if (editedTaskStatus != null)
            setTaskStatus(editedTaskStatus);
        if (editedTaskDescription != null)
            setTaskDescription(editedTaskDescription);
    }

    public UUID getTaskId() {
        return taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
