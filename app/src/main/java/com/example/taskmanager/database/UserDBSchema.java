package com.example.taskmanager.database;

public class UserDBSchema {
    public static final String NAME = "UserDB.db";
    public static final int VERSION = 1;

    public static final class UserTable {

        public static final String NAME = "UserTable";

        public static final class COLS {
            public static final String ID = "id";
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
            public static final String ROLE = "role";
        }
    }
}
