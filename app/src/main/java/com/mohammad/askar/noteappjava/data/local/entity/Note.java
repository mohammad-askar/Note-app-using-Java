package com.mohammad.askar.noteappjava.data.local.entity;

import static com.mohammad.askar.noteappjava.uitls.Constants.TABLE_NAME;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = TABLE_NAME)
public class Note implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String subTitle;
    private String note;
    private String noteData;
    private String notePriority;

    private int noteColor;

    public Note(int id,  String title, String subTitle, String note, String noteData, String notePriority, int noteColor) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.note = note;
        this.noteData = noteData;
        this.notePriority = notePriority;
        this.noteColor = noteColor;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getSubTitle() {
        return subTitle;
    }
    public String getNote() {
        return note;
    }
    public String getNoteData() {
        return noteData;
    }
    public String getNotePriority() {
        return notePriority;
    }

    public int getNoteColor() {
        return noteColor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setNoteData(String noteData) {
        this.noteData = noteData;
    }

    public void setNotePriority(String notePriority) {
        this.notePriority = notePriority;
    }

    public void setNoteColor(int noteColor) {
        this.noteColor = noteColor;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}
