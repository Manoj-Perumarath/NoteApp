package com.manoj.noteapp.utils.ui

import com.manoj.noteapp.data.model.Note

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSelectionVisible: Boolean = false
)
