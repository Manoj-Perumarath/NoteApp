package com.manoj.noteapp.data.usecase

import com.manoj.noteapp.data.model.InvalidNoteException
import com.manoj.noteapp.data.model.Note
import com.manoj.noteapp.data.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repository: NoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("Title cannot be empty")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("Content cannot be empty")
        }
        repository.insertNote(note)
    }
}