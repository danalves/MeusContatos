package com.daniloalvesvieira.meuscontatos.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "contato")
data class Contato(
        @ColumnInfo(name = "nome")
        var nome: String,

        @ColumnInfo(name = "email")
        var email: String,

        @ColumnInfo(name = "telefone")
        var telefone: String,

        @ColumnInfo(name = "endereco")
        var endereco: String) {

    @PrimaryKey(autoGenerate = true)
    var contatoId: Long = 0

}