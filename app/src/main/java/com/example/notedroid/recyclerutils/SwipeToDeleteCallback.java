package com.example.notedroid.recyclerutils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.example.notedroid.R;
import com.example.notedroid.model.Note;
import com.example.notedroid.model.NoteDataBase;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private NoteAdapter noteAdapter;
    private Context context;
    private Drawable icon;
    private final ColorDrawable background;

    public SwipeToDeleteCallback(NoteAdapter noteAdapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.noteAdapter = noteAdapter;
        //icon = ContextCompat.getDrawable(context, R.drawable.outline_delete_black_18dp);
        background = new ColorDrawable(Color.RED);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;
        //int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        //int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        //int iconBottom = iconTop + icon.getIntrinsicHeight();
        if (dX > 0) {
            //int iconLeft = itemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
            //int iconRight = itemView.getLeft() + iconMargin;
            //icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
            background.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + ((int) dX) + backgroundCornerOffset, itemView.getBottom());
        } else if (dX < 0) {
            //int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
            //int iconRight = itemView.getRight() - iconMargin;
            //icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else {
            background.setBounds(0, 0, 0, 0);
        }
        background.draw(c);
        //icon.draw(c);
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
