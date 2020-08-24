package com.example.taskmanager.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.example.taskmanager.database.TaskBaseHelper;
import com.example.taskmanager.database.TaskDBSchema;
import com.example.taskmanager.database.cursorwrapper.TaskCursorWrapper;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.utils.TaskState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TaskDBRepository implements IRepository<Task> {

    private static TaskDBRepository sTaskDBRepository;
    public static Context mContext;
    private SQLiteDatabase mDatabase;

    public static TaskDBRepository getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (sTaskDBRepository == null)
            sTaskDBRepository = new TaskDBRepository();
        return sTaskDBRepository;
    }

    private TaskDBRepository() {
        TaskBaseHelper taskBaseHelper = new TaskBaseHelper(mContext);
        mDatabase = taskBaseHelper.getWritableDatabase();
    }



    @Override
    public List<Task> getList() {
        List<Task> tasks = new ArrayList<>();
        TaskCursorWrapper cursorWrapper = queryTasks(null, null);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                tasks.add(cursorWrapper.getTask());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return tasks;
    }
    @Override
    public List<Task> getList(TaskState taskState) {
        List<Task> tasks = new ArrayList<>();
        TaskCursorWrapper cursorWrapper = queryTasks(null, null);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                tasks.add(cursorWrapper.getTask());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return tasks;
    }

    private TaskCursorWrapper queryTasks(String selection, String[] selectionArgs) {
        Cursor cursor = mDatabase.query(TaskDBSchema.TaskTable.NAME,
                null, selection, selectionArgs, null, null, null);
        TaskCursorWrapper taskCursorWrapper = new TaskCursorWrapper(cursor);
        return taskCursorWrapper;
    }

    @Override
    public Task get(UUID uuid) {
        String selection = TaskDBSchema.TaskTable.COLS.UUID + "=?";
        String[] selectionArgs = new String[]{uuid.toString()};
        TaskCursorWrapper taskCursorWrapper = queryTasks(selection, selectionArgs);
        try {
            taskCursorWrapper.moveToFirst();
            return taskCursorWrapper.getTask();
        } finally {
            taskCursorWrapper.close();
        }
    }

    @Override
    public void setList(List list) {

    }


    @Override
    public void update(Task task) {
        ContentValues values = getTaskContentValue(task);
        String where = TaskDBSchema.TaskTable.COLS.UUID + "=?";
        String[] whereArgs = new String[]{task.getId().toString()};
        mDatabase.update(TaskDBSchema.TaskTable.NAME, values, where, whereArgs);

    }

    private ContentValues getTaskContentValue(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskDBSchema.TaskTable.COLS.UUID, task.getId().toString());
        values.put(TaskDBSchema.TaskTable.COLS.TITLE, task.getTitle());
        values.put(TaskDBSchema.TaskTable.COLS.DESCRIPTION, task.getDescription());
        values.put(TaskDBSchema.TaskTable.COLS.StartDate, task.getStartDate().getTime());
        values.put(TaskDBSchema.TaskTable.COLS.FinishDate, task.getFinishDate().toString());
        values.put(TaskDBSchema.TaskTable.COLS.STATE, task.getTaskState().toString());
        return values;
    }

    @Override
    public void delete(Task task) {
        String where = TaskDBSchema.TaskTable.COLS.UUID + "=?";
        String[] whereArgs = new String[]{task.getId().toString()};
        mDatabase.delete(TaskDBSchema.TaskTable.NAME, where, whereArgs);
    }

    @Override
    public void insert(Task task) {
        ContentValues values = getTaskContentValue(task);
        mDatabase.insert(TaskDBSchema.TaskTable.NAME, null, values);
    }

    @Override
    public void insertList(List list) {

    }

    @Override
    public int getPosition(UUID uuid) {
        List<Task> tasks = getList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(uuid))
                return i;
        }
        return -1;
    }
    @Override
    public void addTask(TaskState taskState) {
        Task task = new Task();
        task.setTitle("Task : " + (sTaskDBRepository.getList().size() + 1));
        task.setDescription("demoTask");
        task.setStartDate(new Date());
        task.setFinishDate(new Date());
        task.setTaskState(taskState);
        ContentValues values = getTaskContentValue(task);
//        mDatabase.insert(TaskDBSchema.TaskTable.NAME, null, values);
        mDatabase.insert(TaskDBSchema.TaskTable.NAME, null, values);
//        Toast.makeText(this, "Create :  "+task.toString(), Toast.LENGTH_SHORT).show();

    }

    private  static TaskState randomTaskState() {
        Random random = new Random();
        int rand = random.nextInt(3);
        if (rand == 0)
            return TaskState.DONE;
        if (rand == 1)
            return TaskState.DOING;
        else
            return TaskState.TODO;
    }
}
