package com.example.notedroid.recyclerutils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.example.notedroid.model.Note;
import com.example.notedroid.model.NoteDataBase;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private NoteAdapter noteAdapter;
    private Context context;

    public SwipeToDeleteCallback(NoteAdapter noteAdapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.noteAdapter = noteAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int position = viewHolder.getAdapterPosition();
        Note delNote = noteAdapter.getNotes().get(position);
        NoteDataBase.getInstance(context).noteDAO().deleteNote(delNote);
        noteAdapter.getNotes().remove(delNote);
        noteAdapter.notifyItemRemoved(position);
    }
}
