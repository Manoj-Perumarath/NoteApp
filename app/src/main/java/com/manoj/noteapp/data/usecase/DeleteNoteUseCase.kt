package com.manoj.noteapp.data.usecase

import com.manoj.noteapp.data.model.Note
import com.manoj.noteapp.data.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}