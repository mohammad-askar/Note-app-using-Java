package com.mohammad.askar.noteappjava.view.adapter;




import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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

import java.util.Random;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {

    NoteViewModel viewModel;
    public NoteslistItemBinding binding;
    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getNote().equals(newItem.getNote()) &&
                    oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getSubTitle().equals(newItem.getSubTitle()) &&
                    oldItem.getNoteData().equals(newItem.getNoteData()) &&
                    oldItem.getNotePriority().equals(newItem.getNotePriority());
        }
    };


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.noteslist_item,
                parent,
                false
        );
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        public NoteViewHolder(@NonNull NoteslistItemBinding binding) {
            super(binding.getRoot());
        }

        public void bind(Note note) {
            binding.noteTitle.setText(note.getTitle());
            binding.noteSubtitle.setText(note.getSubTitle());
            Resources resources = binding.getRoot().getResources();
            int [] colorList = {
                    ResourcesCompat.getColor(resources, R.color.one, null),
                    ResourcesCompat.getColor(resources, R.color.tow, null),
                    ResourcesCompat.getColor(resources, R.color.three, null),
                    ResourcesCompat.getColor(resources, R.color.four, null),
                    ResourcesCompat.getColor(resources, R.color.five, null),
                    ResourcesCompat.getColor(resources, R.color.six, null),
                    ResourcesCompat.getColor(resources, R.color.seven, null),
                    ResourcesCompat.getColor(resources, R.color.eight, null),
                    ResourcesCompat.getColor(resources, R.color.nine, null),
                    ResourcesCompat.getColor(resources, R.color.white, null),
                    ResourcesCompat.getColor(resources, R.color.teen, null)
            };
            Random rn = new Random();
            int index = rn.nextInt(9) + 1;
            binding.cardView.setBackgroundColor(colorList[index]);
            binding.imageViewDelete.setOnClickListener(v -> {
                viewModel.deleteNote(note);
            });

            binding.imageViewEdit.setOnClickListener(v -> {
                viewModel.updateNote(note);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeFragment_to_editNoteFragment);
            });
        }
    }

    public void setViewModel(NoteViewModel viewModel){
        this.viewModel = viewModel;
    }
}
