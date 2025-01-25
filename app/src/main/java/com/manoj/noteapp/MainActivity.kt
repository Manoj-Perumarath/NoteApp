package com.manoj.noteapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.manoj.noteapp.presentation.addeditnote.AddEditNoteScreen
import com.manoj.noteapp.presentation.notes.NotesScreen
import com.manoj.noteapp.ui.theme.NoteAppTheme
import com.manoj.route.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.NOTE_LIST) {
                    Log.d("MainActivity", "Nav changed")
                    composable(Routes.NOTE_LIST) {
                        NotesScreen(navController)
                    }
                    composable(
//                        & noteColor={noteColor}
                        route = Routes.NOTE_ADD_EDIT + "?noteId={noteId}",
                        arguments = listOf(navArgument(name = "noteId") {
                            type = NavType.IntType
                            defaultValue = -1
                        }/*,
                            navArgument(name = "noteColor") {
                                type = NavType.IntType
                                defaultValue = -1
                            })*/
                    )) {
                        AddEditNoteScreen(
                            navController,
                            -1,
                        )
                    }
                }
            }
        }
    }
}
