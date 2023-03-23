package com.mohammad.askar.noteappjava.view;

import static com.mohammad.askar.noteappjava.uitls.Constants.MY_TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.mohammad.askar.noteappjava.R;
import com.mohammad.askar.noteappjava.data.local.entity.Note;
import com.mohammad.askar.noteappjava.viewModel.NoteViewModel;

public class MainActivity extends AppCompatActivity {

    NoteViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        Note mNote = new Note();
        mNote.setId(1);
        mNote.setTitle("Title1");
        mNote.setSubTitle("SubTitle1");
        mNote.setNotePriority("First1");
        mNote.setNoteData("23/03/23");

        Note mNote1 = new Note();
        mNote.setId(2);
        mNote.setTitle("Title2");
        mNote.setSubTitle("SubTitle2");
        mNote.setNotePriority("First2");
        mNote.setNoteData("23/03/23");

        Note mNote2 = new Note();
        mNote.setId(3);
        mNote.setTitle("Title3");
        mNote.setSubTitle("SubTitle3");
        mNote.setNotePriority("First3");
        mNote.setNoteData("23/03/23");

        Note mNote3 = new Note();
        mNote.setId(4);
        mNote.setTitle("Title4");
        mNote.setSubTitle("SubTitle4");
        mNote.setNotePriority("First4");
        mNote.setNoteData("23/03/23");

        Note mNote4 = new Note();
        mNote.setId(5);
        mNote.setTitle("Title5");
        mNote.setSubTitle("SubTitle5");
        mNote.setNotePriority("First5");
        mNote.setNoteData("23/03/23");

        viewModel.insertNote(mNote);
        viewModel.insertNote(mNote1);
        viewModel.insertNote(mNote2);
        viewModel.insertNote(mNote3);
        viewModel.insertNote(mNote4);


        viewModel.getAllNotes();
        viewModel.getNoteById(1);

    }
}