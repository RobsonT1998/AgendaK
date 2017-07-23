package br.com.alexf.agendak.database.dao

import android.arch.persistence.room.*
import br.com.alexf.agendak.model.Aluno
import io.reactivex.Flowable

/**
 * Created by alex on 22/07/17.
 */
@Dao
interface AlunoDAO {
    @Query("SELECT * FROM Aluno")
    fun all(): Flowable<List<Aluno>>

    @Insert
    fun insert(aluno: Aluno)

    @Delete
    fun remove(aluno: Aluno)

    @Update
    fun update(aluno: Aluno)
}