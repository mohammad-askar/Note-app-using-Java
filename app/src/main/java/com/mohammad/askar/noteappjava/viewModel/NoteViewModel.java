package com.mohammad.askar.noteappjava.viewModel;


import static com.mohammad.askar.noteappjava.uitls.Constants.MY_TAG;

import android.app.Application;
import android.media.MediaSession2Service;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mohammad.askar.noteappjava.data.local.entity.Note;
import com.mohammad.askar.noteappjava.data.repository.NoteRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NoteViewModel extends AndroidViewModel {
    private MutableLiveData<List<Note>> _notesList;
    public final LiveData<List<Note>> notesList = _notesList;

    private MutableLiveData<Note> _singleNote;
    public final LiveData<Note> singleNote = _singleNote;
    private final NoteRepository repository;
    private Disposable disposable;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        _notesList = new MutableLiveData<>();
        _singleNote = new MutableLiveData<>();
    }

    public void getAllNotes() {

        disposable = repository.getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onNextNoteList,
                        this::onErrorNote
                );
    }

    public void getNoteById(int id) {
        disposable = repository.getNoteById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccessSingleNote,
                        this::onErrorNote
                );
    }

    public void insertNote(Note note) {

        disposable = repository.insertNote(note)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        this::onCompleteNote,
                        this::onErrorNote
                );
    }

    public void updateNote(Note note) {
        disposable = repository.updateNote(note)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        this::onCompleteNote,
                        this::onErrorNote
                );
    }

    public void deleteNote(Note note) {
        disposable = repository.deleteNote(note)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        this::onCompleteNote,
                        this::onErrorNote
                );

    }

    private void onNextNoteList(List<Note> notes) {
        _notesList.postValue(notes);
        Log.d(MY_TAG, "onNextNoteList : " + notes.get(1).getId());
    }

    private void onErrorNote(Throwable throwable) {
        Log.d(MY_TAG, throwable.getMessage() + "Something went Wrong!!");
    }


    private void onSuccessSingleNote(Note note) {
        _singleNote.postValue(note);
        Log.d(MY_TAG, "onSuccessSingleNote: " + note.getId());
    }

    private void onCompleteNote() {
        Log.d(MY_TAG, "onCompleteNote");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
