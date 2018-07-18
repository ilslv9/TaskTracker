package com.test.ilslv.tasktrackerproject.Domain;

public enum TaskStatus {

    NEW ("Новая"), INPROGRESS ("В процессе"), DONE("Выполнена");

    private String name;

    private TaskStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
