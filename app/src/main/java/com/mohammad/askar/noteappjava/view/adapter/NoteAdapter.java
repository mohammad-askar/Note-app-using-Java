package com.mohammad.askar.noteappjava.view.adapter;


import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammad.askar.noteappjava.R;
import com.mohammad.askar.noteappjava.data.local.entity.Note;
import com.mohammad.askar.noteappjava.databinding.NoteslistItemBinding;
import com.mohammad.askar.noteappjava.viewModel.NoteViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    NoteViewModel viewModel;
    public NoteslistItemBinding binding;
    List<Note> noteList;

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
        setNoteList();

    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.noteslist_item,
                parent,
                false
        );
        setZoomInAnimation(binding.getRoot());
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        holder.bind(viewModel.notesList.getValue().get(position));
        holder.binding.cardView.startAnimation(AnimationUtils.loadAnimation(
                holder.itemView.getContext(),
                R.anim.zoomin
        ));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        NoteslistItemBinding binding;
        public NoteViewHolder(@NonNull NoteslistItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Note note) {
            binding.setNote(note);
            binding.setViewModel(viewModel);

        }
    }

    public void setViewModel(NoteViewModel viewModel) {
        this.viewModel = viewModel;
    }
    public void setNoteList(){
            List<Note> newList = new ArrayList<>(this.noteList);
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MatchItemDiffUtil(noteList, newList));
            result.dispatchUpdatesTo(this);
    }

    private void setZoomInAnimation(View view) {
        Animation zoomIn = AnimationUtils.loadAnimation(binding.getRoot().getContext(), R.anim.zoomin);// animation file
        view.startAnimation(zoomIn);
    }

    public void navigateToCreateFragment() {
        if (viewModel != null)
            viewModel.addNote();
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeFragment_to_createNoteFragment);
    }

    public void navigateFromCreateFragmentToHomeFragment() {
        if (viewModel != null)
            viewModel.addNote();
        Navigation.findNavController(binding.getRoot()).navigateUp();
    }

}
