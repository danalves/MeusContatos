package com.daniloalvesvieira.meuscontatos.room

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.daniloalvesvieira.meuscontatos.model.Contato

@Dao
interface ContatoDao {

    @Query("SELECT * FROM contato ORDER BY nome ASC")
    fun getAll(): List<Contato>

    @Query("SELECT * FROM contato WHERE contatoId IN (:contatoIds)")
    fun loadAllByIds(contatoIds: IntArray): List<Contato>

    @Query("SELECT * FROM contato WHERE nome LIKE :nomeArg LIMIT 1")
    fun findByName(nomeArg: String): Contato

    @Insert
    fun insertAll(vararg contatos: Contato): List<Long>

    @Update(onConflict = REPLACE)
    fun updateContato(contatoEntity: Contato)

    @Delete
    fun delete(contatoEntity: Contato)
}