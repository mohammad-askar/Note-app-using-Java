package com.mohammad.askar.noteappjava.data.local.entity;

import static com.mohammad.askar.noteappjava.uitls.Constants.TABLE_NAME;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = TABLE_NAME)
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String note;
    private String title;
    private String subTitle;
    private String noteData;
    private String notePriority;

    public Note(int id, String note, String title, String subTitle, String noteData, String notePriority) {
        this.id = id;
        this.note = note;
        this.title = title;
        this.subTitle = subTitle;
        this.noteData = noteData;
        this.notePriority = notePriority;
    }

    public int getId() {
        return id;
    }
    public String getNote() {
        return note;
    }
    public String getTitle() {
        return title;
    }
    public String getSubTitle() {
        return subTitle;
    }
    public String getNoteData() {
        return noteData;
    }
    public String getNotePriority() {
        return notePriority;
    }

}
