package com.example.taskmanager.repository;

import com.example.taskmanager.model.User;
import com.example.taskmanager.utils.TaskState;

import java.util.List;
import java.util.UUID;

public class UserDBRepository implements IRepository<User> {
    @Override
    public List<User> getList(TaskState taskState) {
        return null;
    }

    @Override
    public List<User> getList() {
        return null;
    }

    @Override
    public User get(UUID uuid) {
        return null;
    }

    @Override
    public void setList(List<User> list) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void insertList(List<User> list) {

    }

    @Override
    public int getPosition(UUID uuid) {
        return 0;
    }

    @Override
    public void addTask(TaskState e) {

    }


}
