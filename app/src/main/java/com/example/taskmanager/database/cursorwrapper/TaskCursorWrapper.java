package com.example.taskmanager.database.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.taskmanager.database.TaskDBSchema;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.utils.TaskState;

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
        TaskState taskState =TaskState.valueOf(getString(getColumnIndex(TaskDBSchema.TaskTable.COLS.STATE)));

        return new Task(UUID.fromString(stringUUID),title,description,startDate,finishDate, taskState);

    }
//    public Task getTask(TaskState taskStateInput){
//
//        String stringUUID = getString(getColumnIndex(TaskDBSchema.TaskTable.COLS.UUID));
//        String title = getString(getColumnIndex(TaskDBSchema.TaskTable.COLS.TITLE));
//        Date startDate = new Date(getLong(getColumnIndex(TaskDBSchema.TaskTable.COLS.StartDate)));
//        Date finishDate = new Date(getLong(getColumnIndex(TaskDBSchema.TaskTable.COLS.FinishDate)));
//        String description = getString(getColumnIndex(TaskDBSchema.TaskTable.COLS.DESCRIPTION));
////        TaskState taskState =TaskState.valueOf(getString(getColumnIndex(TaskDBSchema.TaskTable.COLS.STATE)));
//        TaskState taskState =taskStateInput;
//
//        return new Task(UUID.fromString(stringUUID),title,description,startDate,finishDate, taskState);
//
//    }
}
