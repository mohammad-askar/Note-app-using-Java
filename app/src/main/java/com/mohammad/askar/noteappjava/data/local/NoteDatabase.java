package com.mohammad.askar.noteappjava.data.local;

import static com.mohammad.askar.noteappjava.uitls.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mohammad.askar.noteappjava.data.local.dao.NoteDao;
import com.mohammad.askar.noteappjava.data.local.entity.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    public static NoteDatabase INSTANCE;

    public static NoteDatabase getDatabaseInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NoteDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return INSTANCE;
    }
}
