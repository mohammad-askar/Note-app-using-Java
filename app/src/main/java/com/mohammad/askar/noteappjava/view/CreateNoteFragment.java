package com.mohammad.askar.noteappjava.view;

import static com.mohammad.askar.noteappjava.uitls.Constants.MY_TAG;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mohammad.askar.noteappjava.R;
import com.mohammad.askar.noteappjava.databinding.FragmentCreateNoteBinding;
import com.mohammad.askar.noteappjava.viewModel.NoteViewModel;


public class CreateNoteFragment extends Fragment {
    private FragmentCreateNoteBinding binding;
    NoteViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_create_note,
                container,
                false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add New Note");
        initViewModel();
        setSaveClickListener();

        return binding.getRoot();
    }

    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void setSaveClickListener(){
        binding.saveBtn.setOnClickListener(view -> {
            if(view != null){
                viewModel.addNote();
                Navigation.findNavController(view).navigateUp();
            }
        });
    }
}