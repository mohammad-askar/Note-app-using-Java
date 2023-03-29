package com.mohammad.askar.noteappjava.view;


import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohammad.askar.noteappjava.R;
import com.mohammad.askar.noteappjava.data.local.entity.Note;
import com.mohammad.askar.noteappjava.databinding.FragmentHomeBinding;
import com.mohammad.askar.noteappjava.view.adapter.NoteAdapter;
import com.mohammad.askar.noteappjava.viewModel.NoteViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    NoteViewModel viewModel;
    NoteAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false
        );
        observeNoteList();
        navigateFromHomeToCreateFragment();
        return binding.getRoot();
    }

    private void navigateFromHomeToCreateFragment(){
        binding.fab.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeFragment_to_createNoteFragment);
        });
    }

    private void observeNoteList(){
        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        viewModel.getAllNotes();
        viewModel.notesList.observe(getViewLifecycleOwner(), this::initAdapter);
    }

    private void initAdapter(List<Note> noteList){
        adapter = new NoteAdapter(noteList);
        adapter.setViewModel(this.viewModel);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setHasFixedSize(true);
        binding.setAdapter(adapter);
    }

}