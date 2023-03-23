package com.mohammad.askar.noteappjava.data.repository;

import android.content.Context;

import com.mohammad.askar.noteappjava.data.local.NoteDatabase;
import com.mohammad.askar.noteappjava.data.local.dao.NoteDao;
import com.mohammad.askar.noteappjava.data.local.entity.Note;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class NoteRepository {

    public NoteDao dao;
    public NoteRepository(Context context) {
        this.dao = NoteDatabase.getDatabaseInstance(context).noteDao();
    }

    public Observable<List<Note>> getAllNotes(){
        return dao.getAllNotes();
    }

    public Single<Note> getNoteById(int id){
        return dao.getNoteById(id);
    }

    public Completable insertNote(Note note){
        return dao.insetNote(note);
    }

    public Completable deleteNote(Note note){
        return dao.deleteNote(note);
    }

    public Completable updateNote(Note note){
        return dao.updateNote(note);
    }
}
