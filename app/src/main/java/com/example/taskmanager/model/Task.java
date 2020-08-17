package com.example.taskmanager.model;

import com.example.taskmanager.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Task implements Serializable {

    private UUID mId;
    private String mTitle;
    private String mDescription;
    private Date mStartDate;
    private Date mFinishDate;
    private boolean mDone;



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

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean done) {
        mDone = done;
    }

    public Task(UUID id, String title, String description, Date startDate, Date finishDate, boolean done) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mStartDate = startDate;
        mFinishDate = finishDate;
        mDone = done;
    }
    public Task(){
        this(UUID.randomUUID());
    }
    public Task(UUID randomUUID) {
        mId = randomUUID;
        mStartDate= DateUtils.getRandomDate(2000, 2020);
    }
}
