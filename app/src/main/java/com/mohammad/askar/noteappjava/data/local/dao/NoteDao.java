package com.mohammad.askar.noteappjava.data.local.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mohammad.askar.noteappjava.data.local.entity.Note;
import java.util.List;
import io.reactivex.Single;
import io.reactivex.rxjava3.core.Completable;

public interface NoteDao {


    @Query("SELECT * FROM note_table ORDER BY id DESC")
    Single<List<Note>> getAllNotes();

    @Query("SELECT * FROM note_table WHERE id =:id")
    Single<Note> getNoteById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insetNote(Note note);

    @Delete
    Completable deleteNote(Note note);

    Completable updateNote(Note note);
}
