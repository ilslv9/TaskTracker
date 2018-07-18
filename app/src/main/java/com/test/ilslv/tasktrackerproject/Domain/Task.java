package com.test.ilslv.tasktrackerproject.Domain;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {

    @PrimaryKey
    private String taskId;
    private String taskTitle;
    private Date taskDate;
    private String taskStatus;
    private String taskDescription;

    public Task(UUID taskId, String taskTitle, Date taskDate, TaskStatus taskStatus, String taskDescription) {
        this.taskId = taskId.toString();
        this.taskTitle = taskTitle;
        this.taskDate = taskDate;
        this.taskStatus = taskStatus.toString();
        this.taskDescription = taskDescription;
    }

    public Task() {
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
        return UUID.fromString(taskId);
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
        return TaskStatus.valueOf(taskStatus);
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus.toString();
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
