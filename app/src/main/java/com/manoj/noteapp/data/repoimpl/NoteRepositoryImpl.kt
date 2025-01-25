package com.manoj.noteapp.data.repoimpl

import com.manoj.noteapp.data.model.Note
import com.manoj.noteapp.data.repository.NoteRepository
import com.manoj.noteapp.data.room.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val dao: NoteDao) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}