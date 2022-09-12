package com.wantech.notes.feature_note.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wantech.notes.feature_note.domain.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
 abstract val noteDao:NoteDao
 companion object{
  val NOTES_DATABASE_NAME="notes_db"
 }
}