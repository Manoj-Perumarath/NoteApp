package com.manoj.noteapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manoj.noteapp.data.model.InvalidNoteException
import com.manoj.noteapp.data.model.Note
import com.manoj.noteapp.data.model.NoteTextFieldState
import com.manoj.noteapp.data.usecase.NoteUseCases
import com.manoj.noteapp.utils.ui.AddEditNoteEvent
import com.manoj.noteapp.utils.ui.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    init {
        if (savedStateHandle.get<Int>("noteId") != null && savedStateHandle.get<Int>("noteId") != -1) {
            viewModelScope.launch {
                noteUseCases.getNoteUseCase(savedStateHandle.get<Int>("noteId")!!)
                    ?.also { note: Note ->
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.intValue = note.color
                    }
            }
        }
    }

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
            text = "",
            hint = "Enter Title",
            isHintVisible = true
        )
    )
    val noteTitle = _noteTitle

    private val _noteContent = mutableStateOf(
        NoteTextFieldState(
            text = "",
            hint = "Enter Content",
            isHintVisible = true
        )
    )
    val noteContent = _noteContent

    private val _noteColor = mutableIntStateOf(Note.noteColors.random().toArgb())
    val noteColor = _noteColor

    private val _uiEventFlow = MutableSharedFlow<UiEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.intValue = event.color
            }

            is AddEditNoteEvent.ChangeContentFocus -> {
                Log.d(
                    "ChangeContentFocus",
                    "focus " + !event.focusState.isFocused + "text " + noteContent.value.text +
                            " " + noteContent.value.text.isNotBlank()
                )
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = (!event.focusState.isFocused && noteContent.value.text.isBlank())
                )
            }

            is AddEditNoteEvent.ChangeTitleFocus -> {
                Log.d(
                    "ChangeTitleFocus",
                    "focus " + !event.focusState.isFocused + "text ${noteTitle.value.text} _ ${_noteTitle.value.text} " + _noteTitle.value.text.isNotBlank()
                )
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value,
                )
            }

            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value,
                )
            }

            AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNoteUseCase(
                            Note(
                                noteTitle.value.text,
                                noteContent.value.text,
                                System.currentTimeMillis(),
                                noteColor.intValue,
                                if (savedStateHandle.get<Int>("noteId") != -1) savedStateHandle.get<Int>(
                                    "noteId"
                                ) else null
                            )
                        )
                        _uiEventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = "Note Saved",
                                action = "Ok"
                            )
                        )
                        _uiEventFlow.emit(UiEvent.PopBackStack)
                    } catch (exception: InvalidNoteException) {
                        _uiEventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = exception.message ?: "",
                                action = "Ok"
                            )
                        )
                    }
                }
            }
        }
    }
}