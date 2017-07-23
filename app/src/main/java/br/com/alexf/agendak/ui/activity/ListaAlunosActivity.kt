package br.com.alexf.agendak.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import br.com.alexf.agendak.R
import br.com.alexf.agendak.application.AgendaKApplication
import br.com.alexf.agendak.model.Aluno
import br.com.alexf.agendak.rx.RxSchedulers
import br.com.alexf.agendak.ui.recyclerview.adapter.AlunosAdapter
import br.com.alexf.agendak.ui.recyclerview.listener.OnItemClickListener
import kotlinx.android.synthetic.main.activity_lista_alunos.*

class ListaAlunosActivity : AppCompatActivity() {

    lateinit var adapter: AlunosAdapter

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_lista_alunos)
        configuraFAB()
    }

    override fun onResume() {
        super.onResume()
        mostraAlunos()
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        var itemId = item?.itemId

        when (itemId) {
            1 -> {
                var aluno = adapter.alunoSelecionado
                var dao = AgendaKApplication.database.alunoDAO()
                RxSchedulers().singleMainThread {
                    aluno?.let { dao.remove(aluno) }
                    mostraAlunos()
                }
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun configuraFAB() {
        adiciona_aluno.setOnClickListener {
            var intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)
        }
    }

    private fun mostraAlunos() {
        var dao = AgendaKApplication.database.alunoDAO()
        RxSchedulers().flowable(dao.all(), {
            alunos ->
            configuraRecyclerView(alunos)
        })
    }

    private fun configuraRecyclerView(alunos: List<Aluno>) {
        adapter = AlunosAdapter(this, alunos,
                onItemClickListener = object : OnItemClickListener<Aluno> {
                    override fun onItemClick(aluno: Aluno) {
                        var intent = Intent(this@ListaAlunosActivity, FormularioActivity::class.java)
                        intent.putExtra("aluno", aluno)
                        this@ListaAlunosActivity.startActivity(intent)
                    }
                })
        lista_alunos.adapter = adapter
        lista_alunos.layoutManager = LinearLayoutManager(this)
    }


}
