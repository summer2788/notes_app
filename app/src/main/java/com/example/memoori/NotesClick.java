package com.example.memoori;

import androidx.cardview.widget.CardView;

import com.example.memoori.Models.Notes;

public interface NotesClick {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);

}
