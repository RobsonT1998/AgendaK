package br.com.alexf.agendak.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.com.alexf.agendak.R
import br.com.alexf.agendak.R.id.menu_formulario_ok
import br.com.alexf.agendak.application.AgendaKApplication
import br.com.alexf.agendak.model.Aluno
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_formulario.*

class FormularioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)
    }

    fun pegaAluno(): Aluno {
        var nome = formulario_nome.text.toString()
        var endereco = formulario_endereco.text.toString()
        var nota = formulario_nota.rating.toDouble()
        var site = formulario_site.text.toString()
        var telefone = formulario_telefone.text.toString()
        return Aluno(nome = nome,
                endereco = endereco,
                nota = nota,
                site = site,
                telefone = telefone)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.formulario_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        when (id) {
            menu_formulario_ok -> {
                var aluno = pegaAluno()
                Single.fromCallable {
                    var database = AgendaKApplication.database
                    database.alunoDAO().insert(aluno)
                }.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

