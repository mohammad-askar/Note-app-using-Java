package com.mohammad.askar.noteappjava;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohammad.askar.noteappjava.databinding.FragmentEditNoteBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditNoteFragment extends Fragment {

    FragmentEditNoteBinding binding;
    public EditNoteFragment() {
        // Required empty public constructor
    }

    public static EditNoteFragment newInstance(String param1, String param2) {
        EditNoteFragment fragment = new EditNoteFragment();
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
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_edit_note,
                container,
                false
        );
        return binding.getRoot();
    }
}