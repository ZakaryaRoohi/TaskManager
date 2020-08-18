package com.example.taskmanager.repository;


import com.example.taskmanager.model.Task;
import com.example.taskmanager.utils.TaskState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TasksRepository implements Serializable {
    private static TasksRepository sTasksRepository;
    private List<Task> mTasks;
    public   static int mNumberOfTasks;

//    public  int getNumberOfTasks() {
//        return mNumberOfTasks;
//    }


    public static TasksRepository getInstance() {
        if (sTasksRepository == null)
            sTasksRepository = new TasksRepository();
        return sTasksRepository;
    }

//    public void setNumberOfTasks(int numberOfTasks) {
//        mNumberOfTasks = numberOfTasks;
//    }

    private TasksRepository() {

        mTasks = new ArrayList<>();
        for (int i = 0; i <mNumberOfTasks ; i++) {
            Task task = new Task();
            task.setTitle("Task : " + (i+1));
            task.setTaskState(randomTaskState());
            mTasks.add(task);
        }
    }
    public List<Task> getList(){
        return mTasks;
    }

    //    private TasksRepository getTasksRepository(TaskState taskState){
//        sTasksRepository
//    }
    private TaskState randomTaskState(){
        Random random = new Random();
        int rand = random.nextInt(3);
        if(rand==0)
            return TaskState.DONE;
        if (rand==1)
            return TaskState.DOING;
        else
            return TaskState.TODO;
    }
    public List<Task> getList(TaskState taskState){
        List<Task> taskList = new ArrayList<>();
        for (Task task: mTasks) {
            if(task.getTaskState()==taskState)
                taskList.add(task);
        }
        return taskList;
    }
    public void addTask(){
        Task task = new Task();
        task.setTitle("Task : " + (sTasksRepository.getList().size()+1));
        task.setTaskState(randomTaskState());
        mTasks.add(task);
    }
    public int getIndexOfTask(Task task){
        return mTasks.indexOf(task);
    }
    public void cleanTaskRepository(){
        sTasksRepository=null;
    }
}
