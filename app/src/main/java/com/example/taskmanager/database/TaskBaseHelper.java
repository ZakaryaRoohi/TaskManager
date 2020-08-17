package com.example.taskmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.taskmanager.database.TaskDBSchema.NAME;

public class TaskBaseHelper extends SQLiteOpenHelper {

    public TaskBaseHelper(@Nullable Context context) {
        super(context, NAME, null, TaskDBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NAME+"("+
                TaskDBSchema.TaskTable.COLS.ID + "integer primary key autoincrement," +
                TaskDBSchema.TaskTable.COLS.UUID + "text,"+
                TaskDBSchema.TaskTable.COLS.TITLE + "text," +
                TaskDBSchema.TaskTable.COLS.StartDate + "long," +
                TaskDBSchema.TaskTable.COLS.FinishDate + "long," +
                TaskDBSchema.TaskTable.COLS.DESCRIPTION + "text," +
                TaskDBSchema.TaskTable.COLS.DONE + "integer" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int i1) {

    }
}
