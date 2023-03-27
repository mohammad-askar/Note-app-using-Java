package com.mohammad.askar.noteappjava.view.adapter;

import androidx.recyclerview.widget.DiffUtil;

import com.mohammad.askar.noteappjava.data.local.entity.Note;

import java.util.List;

public class MatchItemDiffUtil extends DiffUtil.Callback {
    List<Note> oldList;
    List<Note> newList;

    public MatchItemDiffUtil(List<Note> oldList, List<Note> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getNoteData().equals(newList.get(newItemPosition).getNote())
                && oldList.get(oldItemPosition).getNoteColor() == (newList.get(newItemPosition).getNoteColor());
    }
}
