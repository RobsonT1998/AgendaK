package br.com.alexf.agendak.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.com.alexf.agendak.R
import br.com.alexf.agendak.R.id.menu_formulario_ok
import br.com.alexf.agendak.rx.RxSchedulers
import br.com.alexf.agendak.application.AgendaKApplication
import br.com.alexf.agendak.model.Aluno
import kotlinx.android.synthetic.main.activity_formulario.*

class FormularioActivity : AppCompatActivity() {

    lateinit var aluno: Aluno
    var novoAluno: Boolean = true

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_formulario)

        if (intent?.hasExtra("aluno") != null) {
            aluno = intent.extras.getSerializable("aluno") as Aluno
            novoAluno = false
        } else {
            aluno = Aluno()
        }

        preencheCampos()
    }

    fun preencheAluno() {
        var nome = formulario_nome.text.toString()
        var endereco = formulario_endereco.text.toString()
        var nota = formulario_nota.rating.toDouble()
        var site = formulario_site.text.toString()
        var telefone = formulario_telefone.text.toString()
        aluno.nome = nome;
        aluno.endereco = endereco
        aluno.nota = nota
        aluno.site = site
        aluno.telefone = telefone
    }

    fun preencheCampos() {
        formulario_nome.setText(aluno.nome)
        formulario_endereco.setText(aluno.endereco)
        formulario_site.setText(aluno.site)
        formulario_telefone.setText(aluno.telefone)
        formulario_nota.rating = aluno.nota.toFloat()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.formulario_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        when (id) {
            menu_formulario_ok -> {
                preencheAluno()
                var database = AgendaKApplication.database
                if (novoAluno) {
                    RxSchedulers().singleMainThread {
                        database.alunoDAO().insert(aluno)
                    }
                } else {
                    RxSchedulers().singleMainThread {
                        database.alunoDAO().update(aluno)
                    }
                }

                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

