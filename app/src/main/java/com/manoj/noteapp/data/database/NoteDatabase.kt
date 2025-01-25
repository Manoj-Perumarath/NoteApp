package com.manoj.noteapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manoj.noteapp.data.model.Note
import com.manoj.noteapp.data.room.NoteDao

@Database(
    version = 1,
    entities = [Note::class]
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val NOTE_DATABASE = "note_db"
    }
}