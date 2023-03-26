 package com.example.memoori;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.cardview.widget.CardView;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;
 import androidx.recyclerview.widget.StaggeredGridLayoutManager;

 import com.example.memoori.Adapters.NotesListAdapter;
 import com.example.memoori.DataBase.RoomDB;
 import com.example.memoori.Models.Notes;
 import com.google.android.material.floatingactionbutton.FloatingActionButton;

 import java.util.ArrayList;
 import java.util.List;

 public class MainActivity extends AppCompatActivity {

        RecyclerView recyclerView;
        NotesListAdapter notesListAdapter;
        List<Notes> notes= new ArrayList<>();
        RoomDB database;
        FloatingActionButton fab_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler_view);
        fab_btn=findViewById(R.id.fab_add_btn);

        database=RoomDB.getInstance(this);
        notes=database.dao().getAll();
        updateRecycler(notes);

        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, NotesActivity.class);
                startActivityForResult(intent,101);

            }
        });
    }

    private void updateRecycler(List <Notes> notes){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        notesListAdapter= new NotesListAdapter(MainActivity.this,notes,notesClick);
        recyclerView.setAdapter(notesListAdapter);
    }

    private final NotesClick notesClick= new NotesClick() {
        @Override
        public void onClick(Notes notes) {

        }

        @Override
        public void onLongClick(Notes notes, CardView cardView) {

        }
    };
}