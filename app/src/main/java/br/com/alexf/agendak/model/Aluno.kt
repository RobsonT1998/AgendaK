package br.com.alexf.agendak.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by alex on 21/07/17.
 */
@Entity(tableName = "aluno")
data class Aluno(@PrimaryKey val id: String = UUID.randomUUID().toString(),
                 var nome: String = "",
                 var endereco: String = "",
                 var telefone: String = "",
                 var site: String = "",
                 var nota: Double = 0.0,
                 @ColumnInfo(name = "caminho_foto")
                 var caminhoFoto: String = "") {
    @Ignore constructor() : this(id = UUID.randomUUID().toString())
}