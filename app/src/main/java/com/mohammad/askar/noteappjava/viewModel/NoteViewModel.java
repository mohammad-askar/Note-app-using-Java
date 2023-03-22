package com.mohammad.askar.noteappjava.viewModel;


import static com.mohammad.askar.noteappjava.uitls.Constants.MY_TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mohammad.askar.noteappjava.data.local.entity.Note;
import com.mohammad.askar.noteappjava.data.repository.NoteRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NoteViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Note>> _notesList = new MutableLiveData<>();
    public final LiveData<List<Note>> notesList = _notesList;

    private final MutableLiveData<Note> _singleNote = new MutableLiveData<>();
    public final LiveData<Note> singleNote = _singleNote;
    private final NoteRepository repository;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
    }

    public void getAllNotes(){
        repository.getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccess,
                        this::onFailure
                        );
    }

    public void getNoteById(int id){
        repository.getNoteById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(note -> {
                    _singleNote.postValue(note);
                },
                        this::onFailure);
    }

    public void insertNote(Note note){
        repository.insertNote(note)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void updateNote(Note note){
        repository.updateNote(note)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void deleteNote(Note note){
        repository.deleteNote(note)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
    private void onSuccess(List<Note> notes){
        _notesList.postValue(notes);
    }

    private void onFailure(Throwable throwable){
        Log.d(MY_TAG, throwable.getMessage());
    }
}
