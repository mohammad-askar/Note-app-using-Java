package com.mohammad.askar.noteappjava.viewModel;


import static com.mohammad.askar.noteappjava.uitls.Constants.MY_TAG;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mohammad.askar.noteappjava.R;
import com.mohammad.askar.noteappjava.data.local.entity.Note;
import com.mohammad.askar.noteappjava.data.repository.NoteRepository;
import com.mohammad.askar.noteappjava.view.MainActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NoteViewModel extends AndroidViewModel {

    public MutableLiveData<String> title = new MutableLiveData();
    public MutableLiveData<String> subTitle = new MutableLiveData<>();
    public MutableLiveData<String> note = new MutableLiveData<>();
    private final MutableLiveData<List<Note>> _notesList = new MutableLiveData<>();
    public final LiveData<List<Note>> notesList = _notesList;

    private final MutableLiveData<Note> _singleNote = new MutableLiveData<>();
    public final LiveData<Note> singleNote = _singleNote;
    private final NoteRepository repository;

    private Application application;
    private Disposable disposable;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        this.application = application;
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

    public void addNote() {
        String title = this.title.getValue();
        String subTitle = this.subTitle.getValue();
        String note = this.note.getValue();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();
        Note newNote = new Note(0, title, subTitle, note, dateFormat.format(date), "1", getColor());
        insertNote(newNote);
    }

    private void onNextNoteList(List<Note> notes) {
        _notesList.postValue(notes);
        notes.forEach(currentNote ->{
        });
    }

    private void onErrorNote(Throwable throwable) {
        Log.d(MY_TAG, throwable.getMessage() + "Something went Wrong!!");
    }


    private void onSuccessSingleNote(Note note) {
        _singleNote.postValue(note);
    }

    private void onCompleteNote() {
        Log.d(MY_TAG, "onCompleteNote");
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (disposable != null){
            disposable.dispose();
        }
    }

    private int getColor(){
        ;
        int [] colorList = {
                application.getColor(R.color.one),
                application.getColor(R.color.tow),
                application.getColor(R.color.three),
                application.getColor(R.color.four),
                application.getColor(R.color.six),
                application.getColor(R.color.seven),
                application.getColor(R.color.eight),
                application.getColor(R.color.nine),
                application.getColor(R.color.teen)
        };
        Random rn = new Random();
        int index = rn.nextInt(8) + 1;
        return colorList[index];
    }
}
