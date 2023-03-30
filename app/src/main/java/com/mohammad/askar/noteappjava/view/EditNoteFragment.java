package com.mohammad.askar.noteappjava.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mohammad.askar.noteappjava.R;
import com.mohammad.askar.noteappjava.data.local.entity.Note;
import com.mohammad.askar.noteappjava.databinding.FragmentEditNoteBinding;
import com.mohammad.askar.noteappjava.viewModel.NoteViewModel;

public class EditNoteFragment extends Fragment{

    FragmentEditNoteBinding binding;
    private NoteViewModel viewModel;
    private Note noteObject;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_edit_note,
                container,
                false
        );
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Edit Note");
        noteObject = EditNoteFragmentArgs.fromBundle(getArguments()).getNoteObject();
        initViewModel();
        onNoteClicked();

        return binding.getRoot();
    }

    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        viewModel.title.setValue(noteObject.getTitle());
        viewModel.subTitle.setValue(noteObject.getSubTitle());
        viewModel.note.setValue(noteObject.getNote());
        viewModel._singleNote.setValue(noteObject);
        binding.setSingleNote(viewModel);
    }

    public void onNoteClicked() {
        binding.updateNoteBtn.setOnClickListener(v -> {
            Note newNote = viewModel._singleNote.getValue();
            newNote.setTitle(viewModel.title.getValue());
            newNote.setSubTitle(viewModel.subTitle.getValue());
            newNote.setNote(viewModel.note.getValue());
            newNote.setNoteData("Updated "+viewModel.getSimpleDate());
            viewModel.updateNote(newNote);
            Navigation.findNavController(binding.getRoot()).navigateUp();
        });
    }
}