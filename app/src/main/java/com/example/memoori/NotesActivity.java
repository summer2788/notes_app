package com.example.memoori;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memoori.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesActivity extends AppCompatActivity {

    EditText editText_title,editText_notes;
    ImageView image_back;
    Notes notes;
    boolean is_old_note=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        image_back=findViewById(R.id.image_view_back);
        editText_title=findViewById(R.id.edit_text_title);
        editText_notes=findViewById(R.id.edit_text_notes);
        is_old_note=false;

        notes=new Notes();

        try{
            if( getIntent().getSerializableExtra("old_note") != null){
                notes=(Notes) getIntent().getSerializableExtra("old_note");
                editText_title.setText(notes.getTitle());
                editText_notes.setText(notes.getNotes());
                is_old_note=true;
            }


        }catch (Exception e){
            e.printStackTrace();
        }




        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=editText_title.getText().toString();
                String description= editText_notes.getText().toString();


                if(description.isEmpty()){
                    Toast.makeText(NotesActivity.this,"content is empty!",Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat format=new SimpleDateFormat("EEE,d MMM yyyy HH : mm: a");
                Date date= new Date();


                if(!is_old_note){
                    notes=new Notes();
                }

                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(format.format(date));

                Intent intent= new Intent();
                intent.putExtra("note",notes);
                setResult(Activity.RESULT_OK,intent);
                finish();


            }
        });

    }
}