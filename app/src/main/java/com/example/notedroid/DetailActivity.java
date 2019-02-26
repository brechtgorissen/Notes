package com.example.notedroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import com.example.notedroid.model.Converter;
import com.example.notedroid.model.Note;
import com.example.notedroid.model.NoteDataBase;
import java.util.Calendar;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    private EditText title, content;
    private TextView createDate, modDate;
    private Context context;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.mi_save){
            String newTitle = title.getText().toString();
            String newContent = content.getText().toString();
            Date newCreate = Calendar.getInstance().getTime();
            Date newMod = Calendar.getInstance().getTime();
            Note newNote = new Note(newTitle, newContent, newCreate, newMod);
            NoteDataBase.getInstance(context).noteDAO().insertNote(newNote);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = findViewById(R.id.et_title);
        content = findViewById(R.id.et_content);
        createDate = findViewById(R.id.tv_createdate);
        modDate = findViewById(R.id.tv_moddate);
        Note inputNote = (Note) getIntent().getSerializableExtra("note");
        if (inputNote != null){
            title.setText(inputNote.getTitle());
            content.setText(inputNote.getContent());
            createDate.setText(Converter.stringFromDate(inputNote.getCreateDate()));
            modDate.setText(Converter.stringFromDate(inputNote.getModDate()));
        }else{
            createDate.setText(Converter.stringFromDate(new Date()));
            modDate.setText(Converter.stringFromDate(new Date()));
        }
    }
}
