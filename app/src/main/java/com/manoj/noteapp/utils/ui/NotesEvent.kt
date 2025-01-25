package com.manoj.noteapp.utils.ui

import com.manoj.noteapp.data.model.Note

sealed class NotesEvent {

    data class Order(val noteOrder: NoteOrder) : NotesEvent()

    data class DeleteNote(val note: Note) : NotesEvent()

    data object RestoreNote : NotesEvent()

    data object ToggleOrderSelection : NotesEvent()
}
