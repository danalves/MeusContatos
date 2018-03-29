package com.daniloalvesvieira.meuscontatos.room

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database


@Database(entities = arrayOf(ContatoEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contatoDao(): ContatoDao
}