package com.manoj.noteapp.di

import android.app.Application
import androidx.room.Room
import com.manoj.noteapp.data.database.NoteDatabase
import com.manoj.noteapp.data.repoimpl.NoteRepositoryImpl
import com.manoj.noteapp.data.repository.NoteRepository
import com.manoj.noteapp.data.usecase.AddNoteUseCase
import com.manoj.noteapp.data.usecase.DeleteNoteUseCase
import com.manoj.noteapp.data.usecase.GetNoteUseCase
import com.manoj.noteapp.data.usecase.GetNotesUseCase
import com.manoj.noteapp.data.usecase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java, NoteDatabase.NOTE_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(noteRepository),
            deleteNoteUseCase = DeleteNoteUseCase(repository = noteRepository),
            addNoteUseCase = AddNoteUseCase(noteRepository),
            getNoteUseCase = GetNoteUseCase(noteRepository)
        )
    }
}