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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.mohammad.askar.noteappjava.R;
import com.mohammad.askar.noteappjava.data.local.entity.Note;
import com.mohammad.askar.noteappjava.databinding.FragmentHomeBinding;
import com.mohammad.askar.noteappjava.view.adapter.NoteAdapter;
import com.mohammad.askar.noteappjava.viewModel.NoteViewModel;

import java.util.List;

public class HomeFragment extends Fragment implements MenuProvider {

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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");
        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.CREATED);
        setHasOptionsMenu(true);
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


    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {

        menuInflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(" Search for Note");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);

                return false;
            }
        });
        searchView.clearFocus();
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}