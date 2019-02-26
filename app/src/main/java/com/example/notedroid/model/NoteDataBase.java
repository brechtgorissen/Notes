package com.example.notedroid.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(version = 1, entities = {Note.class}, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class NoteDataBase extends RoomDatabase{

    private static NoteDataBase ourInstance;

    public static NoteDataBase getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = createDatabase(context);
        }
        return ourInstance;
    }

    private static NoteDataBase createDatabase(Context context) {
        return Room.databaseBuilder(context, NoteDataBase.class, "notes.db").allowMainThreadQueries().build();
    }

    public abstract NoteDAO noteDAO();
}


