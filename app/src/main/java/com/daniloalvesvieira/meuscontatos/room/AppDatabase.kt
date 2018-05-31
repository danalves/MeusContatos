package com.daniloalvesvieira.meuscontatos.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.daniloalvesvieira.meuscontatos.model.Contato


@Database(entities = arrayOf(Contato::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contatoDao(): ContatoDao
}