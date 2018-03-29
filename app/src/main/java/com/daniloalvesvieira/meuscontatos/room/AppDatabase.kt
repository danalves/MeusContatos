package com.daniloalvesvieira.meuscontatos.room

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database


@Database(entities = Array<ContatoEntity::class.java>(), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}