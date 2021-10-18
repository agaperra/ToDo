package com.agaperra.todo.di

import android.content.Context
import androidx.room.Room
import com.agaperra.todo.data.db.INoteDatabaseHelper
import com.agaperra.todo.data.db.NoteDatabase
import com.agaperra.todo.data.db.NoteDatabaseHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  DataModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): NoteDatabase = Room
        .databaseBuilder(context, NoteDatabase::class.java, "note_database")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideYourDao(db: NoteDatabase) = db.noteDao()

}