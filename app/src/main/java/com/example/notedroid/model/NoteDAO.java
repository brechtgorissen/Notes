package com.example.notedroid.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void insertNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Update
    void updateNote(Note nte);

    @Query("SELECT * FROM Note")
    List<Note> selectAllNotes();

    @Query("SELECT * FROM Note WHERE id = :id")
    Note selectNoteByID(int id);
}

