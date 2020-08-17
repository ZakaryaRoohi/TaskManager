package com.example.taskmanager.database;

public class TaskDBSchema {
    public static final String NAME = "TaskDB.db";
    public static final int VERSION = 1;
    public static final class TaskTable {
public static final String NAME="TaskTable";

        public static final class COLS {
            public static final String ID = "id";
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String StartDate = "startDate";
            public static final String FinishDate = "finishDate";
            public static final String DESCRIPTION = "description";
            public static final String DONE = "done";
        }
    }

}
