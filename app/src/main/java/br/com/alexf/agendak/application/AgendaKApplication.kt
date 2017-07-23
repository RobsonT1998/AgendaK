package br.com.alexf.agendak.application

import android.app.Application
import android.arch.persistence.room.Room
import br.com.alexf.agendak.database.AppDatabase

/**
 * Created by alex on 22/07/17.
 */
class AgendaKApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        val dbName = "AgendaK"
        database = Room.databaseBuilder(this,
                AppDatabase::class.java, dbName).build()
    }


}