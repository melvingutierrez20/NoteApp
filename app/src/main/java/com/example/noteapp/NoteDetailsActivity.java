package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class NoteDetailsActivity extends AppCompatActivity {

    EditText tittleEditText,contentEditText;
    ImageButton saveNoteBtn;
    TextView pageTitleTextView;
    String title,content,docId;
    boolean isEditMode = false;
    TextView deleteNoteTextViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        tittleEditText = findViewById(R.id.note_title);
        contentEditText = findViewById(R.id.note_content);
        saveNoteBtn = findViewById(R.id.save_note_btn);
        pageTitleTextView = findViewById(R.id.page_title);
        deleteNoteTextViewBtn = findViewById(R.id.delete_note_txt);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        docId = getIntent().getStringExtra("docId");

        if(docId!=null && !docId.isEmpty()){
            isEditMode = true;
        }

        tittleEditText.setText(title);
        contentEditText.setText(content);
        if(isEditMode){
            pageTitleTextView.setText("Edita tu nota");
            deleteNoteTextViewBtn.setVisibility(View.VISIBLE);
        }
        saveNoteBtn.setOnClickListener((v)-> saveNote());

        deleteNoteTextViewBtn.setOnClickListener((v)-> deleteNoteFromFirebase());
    }

    void saveNote(){
        String noteTitle = tittleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();
        if (noteTitle==null || noteTitle.isEmpty()){
            tittleEditText.setError("Título es requerido");
            return;
        }
        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());

        saveNoteToFirebase(note);

    }

    void saveNoteToFirebase(Note note){
        DocumentReference documentReference;
        if(isEditMode){
            documentReference = Utility.getCollectionReferenceForNotes().document(docId);
        }else{
            documentReference = Utility.getCollectionReferenceForNotes().document();
        }



        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    Utility.showToast(NoteDetailsActivity.this, "Nota agregada satisfactoriamente");
                    finish();
                }else{
                    Utility.showToast(NoteDetailsActivity.this, "Fallo al agregar la nota");
                }
            }
        });
    }

    void deleteNoteFromFirebase(){
            DocumentReference documentReference;
                documentReference = Utility.getCollectionReferenceForNotes().document(docId);

            documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                        Utility.showToast(NoteDetailsActivity.this, "Nota borrada satisfactoriamente");
                        finish();
                    }else{
                        Utility.showToast(NoteDetailsActivity.this, "Fallo al borrar la nota");
                    }
                }
            });

    }

}