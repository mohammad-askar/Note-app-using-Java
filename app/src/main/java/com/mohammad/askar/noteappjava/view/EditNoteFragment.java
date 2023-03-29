package com.mohammad.askar.noteappjava.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mohammad.askar.noteappjava.R;
import com.mohammad.askar.noteappjava.data.local.entity.Note;
import com.mohammad.askar.noteappjava.databinding.FragmentEditNoteBinding;
import com.mohammad.askar.noteappjava.view.adapter.NoteAdapter;
import com.mohammad.askar.noteappjava.viewModel.NoteViewModel;

public class EditNoteFragment extends Fragment{

    FragmentEditNoteBinding binding;
    private NoteViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_edit_note,
                container,
                false
        );
        Note noteObject = EditNoteFragmentArgs.fromBundle(getArguments()).getNoteObject();

        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        Toast.makeText(this.requireContext(), noteObject.getTitle()+"tow", Toast.LENGTH_SHORT).show();
        viewModel.title.setValue(noteObject.getTitle());
        viewModel.subTitle.setValue(noteObject.getSubTitle());
        viewModel.note.setValue(noteObject.getNote());
        viewModel._singleNote.setValue(noteObject);
        binding.setSingleNote(viewModel);
        onNoteClicked();

        return binding.getRoot();
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