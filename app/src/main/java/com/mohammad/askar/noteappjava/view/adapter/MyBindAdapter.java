package com.mohammad.askar.noteappjava.view.adapter;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.navigation.Navigation;

import com.mohammad.askar.noteappjava.R;
import com.mohammad.askar.noteappjava.viewModel.NoteViewModel;

public class MyBindAdapter {

    @BindingAdapter({"setNoteViewModel"})
    public static void navigateToCreateFragment( View view, NoteViewModel viewModel) {
        if (viewModel != null)
        viewModel.addNote();
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_createNoteFragment);
    }
}
