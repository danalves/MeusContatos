package com.daniloalvesvieira.meuscontatos.room

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface ContatoDao {

    @Query("SELECT * FROM contato")
    fun getAll(): List<ContatoEntity>

    @Query("SELECT * FROM contato WHERE contatoId IN (:contatoIds)")
    fun loadAllByIds(contatoIds: IntArray): List<ContatoEntity>

    @Query("SELECT * FROM contato WHERE nome LIKE :nomeArg LIMIT 1")
    fun findByName(nomeArg: String): ContatoEntity

    @Insert
    fun insertAll(vararg contatos: ContatoEntity)

    @Update(onConflict = REPLACE)
    fun updateTask(contatoEntity: ContatoEntity)

    @Delete
    fun delete(contatoEntity: ContatoEntity)
}