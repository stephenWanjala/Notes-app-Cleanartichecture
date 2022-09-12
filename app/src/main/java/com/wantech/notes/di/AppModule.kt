package com.wantech.notes.di

import android.app.Application
import androidx.room.Room
import com.wantech.notes.feature_note.data.datasource.NoteDatabase
import com.wantech.notes.feature_note.data.repository.NoteRepositoryImpl
import com.wantech.notes.feature_note.domain.repository.NoteRepository
import com.wantech.notes.feature_note.domain.usecase.CreateNoteUseCase
import com.wantech.notes.feature_note.domain.usecase.DeleteNoteUseCase
import com.wantech.notes.feature_note.domain.usecase.GetNotesUseCase
import com.wantech.notes.feature_note.domain.usecase.NotesUseCases
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
    fun provideNoteDatabase(app: Application): NoteDatabase = Room.databaseBuilder(
        app,
        NoteDatabase::class.java,
        NoteDatabase.NOTES_DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideNotesRepository(db: NoteDatabase): NoteRepository = NoteRepositoryImpl(db.noteDao)

    @Provides
    @Singleton
    fun provideNotesUseCases(repository: NoteRepository): NotesUseCases = NotesUseCases(
        getNotesUseCase = GetNotesUseCase(repository),
        deleteNoteUseCase = DeleteNoteUseCase(repository),
        addNotesUseCase = CreateNoteUseCase(repository)
    )
}