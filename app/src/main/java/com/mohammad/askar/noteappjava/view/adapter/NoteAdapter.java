package com.mohammad.askar.noteappjava.view.adapter;


import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.mohammad.askar.noteappjava.R;
import com.mohammad.askar.noteappjava.data.local.entity.Note;
import com.mohammad.askar.noteappjava.databinding.FragmentHomeBinding;
import com.mohammad.askar.noteappjava.databinding.NoteslistItemBinding;
import com.mohammad.askar.noteappjava.view.EditNoteFragment;
import com.mohammad.askar.noteappjava.view.EditNoteFragmentDirections;
import com.mohammad.askar.noteappjava.view.HomeFragment;
import com.mohammad.askar.noteappjava.view.HomeFragmentDirections;
import com.mohammad.askar.noteappjava.view.MainActivity;
import com.mohammad.askar.noteappjava.viewModel.NoteViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> implements Filterable {

    private NoteViewModel viewModel;
    public NoteslistItemBinding binding;
    List<Note> noteList;
    List<Note> filteredList;
    private View parent;
    private Note singleNote;
    ValueFilter valueFilter;
    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
        this.filteredList = noteList;
//        setNoteList();
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
        this.parent = parent;
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        this.singleNote = noteList.get(position);
        holder.bind(singleNote);
        this.binding.setAdapter(this);

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
//            this.binding.setViewModel(viewModel);
            this.binding.setNote(singleNote);
            setONEditListener(note);
            setDeleteSnackBar(parent, note);
        }
    }

    public void setViewModel(NoteViewModel viewModel) {
        this.viewModel = viewModel;
    }
    public void setNoteList(){
            List<Note> newList = new ArrayList<>(this.noteList);
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MatchItemDiffUtil(filteredList, noteList));
            result.dispatchUpdatesTo(this);
    }

    private void setZoomInAnimation(View view) {
        Animation zoomIn = AnimationUtils.loadAnimation(binding.getRoot().getContext(), R.anim.zoomin);// animation file
        view.startAnimation(zoomIn);

    }

    private void setONEditListener(Note note){
        binding.imageViewEdit.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToEditNoteFragment action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(note);
            Navigation.findNavController(binding.getRoot()).navigate(action);
        });
    }

    public void setDeleteSnackBar(View parent, Note noteToDelete){

        binding.imageViewDelete.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(parent, "Delete a Note?", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Delete", snackBarDeleteView -> {
                        viewModel.deleteNote(noteToDelete);
                        Snackbar snackBarDelete = Snackbar.make(parent, " Note is Deleted", Snackbar.LENGTH_LONG)
                                .setAction("Undo", snackBarUndoView -> {
                                    viewModel.insertNote(noteToDelete);
                                })
                                .setActionTextColor(v.getResources().getColor(R.color.green, v.getContext().getTheme()));
                        snackBarDelete.show();
                    })
                    .setActionTextColor(v.getResources().getColor(R.color.red, v.getContext().getTheme()));
            snackbar.show();
        });
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<Note> filterList = new ArrayList<>();
                for (int i = 0; i < filteredList.size(); i++) {
                    if (!filteredList.get(i).getTitle().isEmpty() &&
                            filteredList.get(i).getTitle().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                            !filteredList.get(i).getSubTitle().isEmpty() &&
                                    filteredList.get(i).getSubTitle().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                            !filteredList.get(i).getNote().isEmpty() &&
                                    filteredList.get(i).getNote().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        filterList.add(filteredList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = filteredList.size();
                results.values = filteredList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
                noteList = (List<Note>) results.values;
                setNoteList();
        }
    }
}
