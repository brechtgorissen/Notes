package com.example.notedroid.recyclerutils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.notedroid.R;
import com.example.notedroid.model.Converter;
import com.example.notedroid.model.Note;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> notes;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{

        public final TextView tvTitle, tvModDate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvModDate = itemView.findViewById(R.id.tv_moddate);
        }
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View cardRowView = LayoutInflater.from(context).inflate(R.layout.note_card, viewGroup, false);
        return new NoteViewHolder(cardRowView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder viewHolder, int i) {
        Note noteForRow = notes.get(i);
        String dateAsString = Converter.stringFromDate(noteForRow.getModDate());
        viewHolder.tvTitle.setText(noteForRow.getTitle());
        viewHolder.tvModDate.setText(dateAsString);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
