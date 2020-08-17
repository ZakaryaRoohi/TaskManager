package com.example.taskmanager.database.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.taskmanager.database.TaskDBSchema;
import com.example.taskmanager.model.Task;

import java.util.Date;
import java.util.UUID;

public class TaskCursorWrapper  extends CursorWrapper {

    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Task getTask(){
        String stringUUID = getString(getColumnIndex(TaskDBSchema.TaskTable.COLS.UUID));
        String title = getString(getColumnIndex(TaskDBSchema.TaskTable.COLS.TITLE));
        Date startDate = new Date(getLong(getColumnIndex(TaskDBSchema.TaskTable.COLS.StartDate)));
        Date finishDate = new Date(getLong(getColumnIndex(TaskDBSchema.TaskTable.COLS.FinishDate)));
        String description = getString(getColumnIndex(TaskDBSchema.TaskTable.COLS.DESCRIPTION));
        boolean done = getInt(getColumnIndex(TaskDBSchema.TaskTable.COLS.DONE)) == 0 ? false : true;

        Task task = new Task(UUID.fromString(stringUUID),title,description,startDate,finishDate,done);
        return task;

    }
}
