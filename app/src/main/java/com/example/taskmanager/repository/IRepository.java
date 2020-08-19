package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.utils.TaskState;

import java.util.List;
import java.util.UUID;

public interface IRepository<E> {
    List<E> getList(TaskState taskState);
    List<E> getList();
    E get(UUID uuid);
    void setList(List<E> list);
    void update(E e);
    void delete(E e);
    void insert(E e);
    void insertList(List<E> list);
    int getPosition(UUID uuid);
    void addTask();
}
