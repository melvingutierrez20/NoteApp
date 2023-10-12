package com.example.noteapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter {
    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView,contentTextView,timestampTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.note_title_txt);
            contentTextView = itemView.findViewById(R.id.note_content_txt);
            timestampTextView = itemView.findViewById(R.id.note_time_txt);
        }
    }
}
