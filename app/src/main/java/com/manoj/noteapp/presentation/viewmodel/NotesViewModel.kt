package com.manoj.noteapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manoj.noteapp.data.model.Note
import com.manoj.noteapp.data.usecase.NoteUseCases
import com.manoj.noteapp.utils.ui.NoteOrder
import com.manoj.noteapp.utils.ui.NoteState
import com.manoj.noteapp.utils.ui.NotesEvent
import com.manoj.noteapp.utils.ui.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteUseCases: NoteUseCases) : ViewModel() {

    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state
    private var deletedNote: Note? = null
    private var notesJob: Job? = null

    init {
        getNotesOrder(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    event.note.id?.let {
                        noteUseCases.deleteNoteUseCase(note = event.note)
                        deletedNote = event.note
                    }
                }
            }

            is NotesEvent.Order -> {
                if (_state.value.noteOrder::class == event.noteOrder.orderType::class
                    && _state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                viewModelScope.launch {
                    getNotesOrder(event.noteOrder)
                }
            }

            NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNoteUseCase(deletedNote ?: return@launch)
                    deletedNote = null
                }
            }

            NotesEvent.ToggleOrderSelection -> {
                _state.value = _state.value.copy(
                    isOrderSelectionVisible = !_state.value.isOrderSelectionVisible
                )
            }
        }
    }

    private fun getNotesOrder(noteOrder: NoteOrder) {
        notesJob?.cancel()
        notesJob = noteUseCases.getNotesUseCase(noteOrder).onEach { notes ->
            _state.value = _state.value.copy(
                notes = notes,
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)
    }
}