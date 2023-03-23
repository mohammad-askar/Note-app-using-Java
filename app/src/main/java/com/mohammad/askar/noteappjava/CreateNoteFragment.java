package com.mohammad.askar.noteappjava;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohammad.askar.noteappjava.databinding.FragmentCreateNoteBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateNoteFragment extends Fragment {
    FragmentCreateNoteBinding binding;
    public CreateNoteFragment() {
        // Required empty public constructor
    }

    public static CreateNoteFragment newInstance(String param1, String param2) {
        CreateNoteFragment fragment = new CreateNoteFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_create_note,
                container,
                false);
        return binding.getRoot();
    }
}