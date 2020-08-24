package com.example.taskmanager.model;

import com.example.taskmanager.utils.DateUtils;
import com.example.taskmanager.utils.TaskState;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Task implements Serializable {

    private UUID mId;
    private String mTitle;
    private String mDescription;
    private Date mStartDate;
    private Date mFinishDate;
    private TaskState mTaskState;


    public TaskState getTaskState() {
        return mTaskState;
    }
    public void setTaskState(TaskState taskState) {
        mTaskState = taskState;
    }
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date startDate) {
        mStartDate = startDate;
    }

    public Date getFinishDate() {
        return mFinishDate;
    }

    public void setFinishDate(Date finishDate) {
        mFinishDate = finishDate;
    }


    public Task(UUID id, String title, String description, Date startDate, Date finishDate, TaskState taskState) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mStartDate = startDate;
        mFinishDate = finishDate;
        mTaskState = taskState;
    }
    public Task(){
        this(UUID.randomUUID());
    }

    public Task(UUID randomUUID) {
        mId = randomUUID;
        mStartDate= DateUtils.getRandomDate(2000, 2020);
    }

    @Override
    public String toString() {
        return "Task{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mStartDate=" + mStartDate +
                ", mFinishDate=" + mFinishDate +
                ", mTaskState=" + mTaskState +
                '}';
    }
}
