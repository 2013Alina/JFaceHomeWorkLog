package com.homeworklog;

public class Student {
    private String name;
    private int group;
    private boolean done;

    public Student(String name, int group, boolean done) {
        this.name = name;
        this.group = group;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
