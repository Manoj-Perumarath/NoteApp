package com.manoj.noteapp.data.usecase

import com.manoj.noteapp.data.model.Note
import com.manoj.noteapp.data.repository.NoteRepository
import com.manoj.noteapp.utils.ui.NoteOrder
import com.manoj.noteapp.utils.ui.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val repository: NoteRepository) {

    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when (noteOrder) {
                is NoteOrder.Color -> {
                    when (noteOrder.orderType) {
                        OrderType.Ascending -> notes.sortedBy { it.color }
                        OrderType.Descending -> notes.sortedByDescending { it.color }
                    }
                }

                is NoteOrder.Date -> {
                    when (noteOrder.orderType) {
                        OrderType.Ascending -> notes.sortedBy { it.timeStamp }

                        OrderType.Descending -> notes.sortedByDescending { it.timeStamp }
                    }
                }

                is NoteOrder.Title -> {
                    when (noteOrder.orderType) {
                        OrderType.Ascending -> notes.sortedBy { it.title.lowercase() }

                        OrderType.Descending -> notes.sortedByDescending { it.title.lowercase() }
                    }
                }
            }
        }
    }
}