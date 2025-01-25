package com.manoj.noteapp.presentation.addeditnote

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.manoj.noteapp.data.model.Note
import com.manoj.noteapp.presentation.components.TransparentEditText
import com.manoj.noteapp.presentation.viewmodel.AddEditNotesViewModel
import com.manoj.noteapp.utils.ui.AddEditNoteEvent
import com.manoj.noteapp.utils.ui.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    navController: NavController, noteColor: Int, viewModel: AddEditNotesViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val scaffoldState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(
                if (noteColor != -1) {
                    noteColor
                } else viewModel.noteColor.intValue
            )
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEventFlow.collectLatest { value: UiEvent ->
            when (value) {
                is UiEvent.Navigate -> {}
                is UiEvent.PopBackStack -> {
                    navController.navigateUp()
                }

                is UiEvent.ShowSnackBar -> {
                    scaffoldState.showSnackbar(
                        message = value.message, actionLabel = value.action
                    )
                }
            }
        }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            viewModel.onEvent(AddEditNoteEvent.SaveNote)
        }) {
            Icon(imageVector = Icons.Default.Save, contentDescription = "Save Note")
        }
    }, snackbarHost = { SnackbarHost(hostState = scaffoldState) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.noteColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(modifier = Modifier
                        .size(50.dp)
                        .shadow(15.dp, CircleShape)
                        .clip(CircleShape)
                        .background(color)

                        .clickable {
                            scope.launch {
                                noteBackgroundAnimatable.animateTo(
                                    targetValue = Color(colorInt), animationSpec = tween(
                                        durationMillis = 500
                                    )
                                )
                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                            }
                        })
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            TransparentEditText(
                text = titleState.text,
                onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it)) },
                onFocusChange = { viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it)) },
                hint = titleState.hint,
                isHintVisible = titleState.isHintVisible,
                textStyle = MaterialTheme.typography.headlineMedium,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            TransparentEditText(
                modifier = Modifier.fillMaxSize(),
                text = contentState.text,
                onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnteredContent(it)) },
                onFocusChange = { viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it)) },
                hint = contentState.hint,
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.bodySmall,
                singleLine = false,
            )
        }
    }
}