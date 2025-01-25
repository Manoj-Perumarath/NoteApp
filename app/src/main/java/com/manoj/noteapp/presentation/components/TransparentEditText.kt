package com.manoj.noteapp.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction

@Composable
fun TransparentEditText(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit,
    onValueChange: (String) -> Unit

) {
//    val focusRequester = remember { FocusRequester() }

    SideEffect {
        Log.d("TransparentEditText", text + hint + "isHintVisible " + isHintVisible)
    }
    Box(
        modifier = modifier
//            .focusRequester(focusRequester),
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChange(it) },
            singleLine = singleLine,
            keyboardOptions = if (singleLine) {
                KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            } else {
                KeyboardOptions.Default
            },
        )
        if (isHintVisible) {
            Text(text = hint, style = textStyle, color = Color.DarkGray)
        }
    }
}