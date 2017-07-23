package br.com.alexf.agendak.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.alexf.agendak.database.dao.AlunoDAO
import br.com.alexf.agendak.model.Aluno

/**
 * Created by alex on 22/07/17.
 */
@Database(entities = arrayOf(Aluno::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun alunoDAO(): AlunoDAO
}