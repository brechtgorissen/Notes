package com.example.notedroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.notedroid.model.Note;
import com.example.notedroid.model.NoteDataBase;
import com.example.notedroid.recyclerutils.NoteAdapter;
import com.example.notedroid.recyclerutils.RecyclerTouchListener;
import com.example.notedroid.recyclerutils.SwipeToDeleteCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNotes;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.mi_add){

            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvNotes = findViewById(R.id.rv_notes);
        final List<Note> items = NoteDataBase.getInstance(getApplicationContext()).noteDAO().selectAllNotes();
        NoteAdapter noteAdapter = new NoteAdapter(items);
        rvNotes.setAdapter(noteAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvNotes.setLayoutManager(layoutManager);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(noteAdapter));
        itemTouchHelper.attachToRecyclerView(rvNotes);
        rvNotes.addOnItemTouchListener(new RecyclerTouchListener(this, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Note touchedNote = new Note();
                touchedNote.setTitle(items.get(position).getTitle());
                touchedNote.setContent(items.get(position).getContent());
                touchedNote.setCreateDate(items.get(position).getCreateDate());
                touchedNote.setModDate(items.get(position).getModDate());

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("note", touchedNote);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }));
    }
}