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
}
