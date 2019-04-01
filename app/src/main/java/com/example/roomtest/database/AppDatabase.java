package com.example.roomtest.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {RoomUser.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RoomUserDao userDao();

    private static AppDatabase INSTANCE;
    private static final Object sLock = new Object();


    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user.db")
                                .allowMainThreadQueries()
                                .build();
            }


            return INSTANCE;
        }
    }
}
