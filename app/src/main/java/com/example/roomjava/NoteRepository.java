package com.example.roomjava;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDataBase dataBase = NoteDataBase.getInstance(application);
        noteDao = dataBase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note){
        new InsertNoteAsycTask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsycTask(noteDao).execute(note);

    }
    public void delete(Note note){
        new DeleteNoteAsycTask(noteDao).execute(note);

    }
    public void deleteAllNoyes(Note note){
        new DeleteAllNoteAsycTask(noteDao).execute();

    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class InsertNoteAsycTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao;
        private InsertNoteAsycTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class UpdateNoteAsycTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao;
        private UpdateNoteAsycTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteNoteAsycTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao;
        private DeleteNoteAsycTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllNoteAsycTask extends AsyncTask<Void, Void, Void>{
        private NoteDao noteDao;
        private DeleteAllNoteAsycTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deletAllNotes();
            return null;
        }
    }
}
