package com.mohammad.askar.noteappjava.data.repository;

import android.content.Context;

import com.mohammad.askar.noteappjava.data.local.NoteDatabase;
import com.mohammad.askar.noteappjava.data.local.dao.NoteDao;
import com.mohammad.askar.noteappjava.data.local.entity.Note;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.rxjava3.core.Completable;

public class NoteRepository {

    public NoteDao dao;
    public Single<List<Note>> notesList;

    public NoteRepository(Context context) {
        this.dao = NoteDatabase.getDatabaseInstance(context).noteDao();
    }

    Single<List<Note>> getAllNotes(){
        return dao.getAllNotes();
    }

    Single<Note> getNoteById(int id){
        return dao.getNoteById(id);
    }

    Completable insertNote(Note note){
        return dao.insetNote(note);
    }

    Completable deleteNote(Note note){
        return dao.deleteNote(note);
    }

    Completable updateNote(Note note){
        return dao.updateNote(note);
    }
}
