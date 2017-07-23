package br.com.alexf.agendak.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.alexf.agendak.R
import br.com.alexf.agendak.model.Aluno
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.list_item.*;

/**
 * Created by alex on 21/07/17.
 */
class AlunosAdapter(val context: Context, val alunos: List<Aluno>) : RecyclerView.Adapter<AlunosAdapter.ViewHolder>() {

    var alunoSelecionado: Aluno? = null
        private set

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aluno = alunos.get(position)
        holder.nome.text = aluno.nome
        holder.telefone.text = aluno.telefone
        holder.itemView.setOnLongClickListener {
            alunoSelecionado = alunos.get(position)
            false
        }
    }

    override fun getItemCount(): Int {
        return alunos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {

        val foto = itemView.findViewById(R.id.item_foto) as CircleImageView
        val nome = itemView.findViewById(R.id.item_nome) as TextView
        val telefone = itemView.findViewById(R.id.item_telefone) as TextView

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu.add(0, 1, 0, "Remove")
        }

    }

}