package com.example.roomexample.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BukuModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase appDatabase;

    public abstract BukuDAO bukuDAO();

    public static AppDatabase iniDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    "buku"
            ).allowMainThreadQueries().build();
        }
        return appDatabase;
    }

}