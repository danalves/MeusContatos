package com.daniloalvesvieira.meuscontatos.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.daniloalvesvieira.meuscontatos.MapActivity
import com.daniloalvesvieira.meuscontatos.R
import com.daniloalvesvieira.meuscontatos.listener.OnItemClickListener
import com.daniloalvesvieira.meuscontatos.model.Contato


class ContatosAdapter(_context: Context,_contatos: List<Contato>) : RecyclerView.Adapter<ContatosAdapter.ContatoItemViewHolder>() {

    private var context: Context
    private var layoutInflater: LayoutInflater
    private var contatos: List<Contato>? = null
    private var listener: OnItemClickListener? = null
    private var posicaoContextMenu: Int

    init{
        context = _context
        layoutInflater = LayoutInflater.from(context)
        contatos = _contatos
        posicaoContextMenu = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoItemViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_contato, parent, false)
        val contatoItemViewHolder = ContatoItemViewHolder(itemView)
        return contatoItemViewHolder
    }

    override fun onBindViewHolder(holder: ContatoItemViewHolder, position: Int) {
        val contato = contatos!!.get(position)
        holder.tvNomeContato.setText(contato.nome)
        holder.tvEmailContato.setText(contato.email)
        holder.tvNumeroContato.setText(contato.telefone)
        holder.ivFotoContato.setImageResource(R.drawable.contact)
        holder.contato = contato

        holder.itemView.setOnLongClickListener {
            setPosicaoContextMenu(holder.adapterPosition)
            return@setOnLongClickListener false
        }

//        Picasso.with(context).load(android.getUrlImagem())
//                .error(R.drawable.cancel)
//                .placeholder(R.drawable.loading)
//                .into(holder.ivLogo)

    }

    fun setPosicaoContextMenu(posicao: Int) {
        posicaoContextMenu = posicao
    }

    fun getPosicaoContextMenu(): Int {
        return posicaoContextMenu
    }

    fun getItem(position: Int): Contato {
        return contatos!!.get(position)
    }

    override fun getItemCount(): Int {
        return contatos!!.size
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.listener = listener

    }

    inner class ContatoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
                                                        View.OnClickListener,
                                                        View.OnCreateContextMenuListener{

        var ivFotoContato: ImageView
        var tvNomeContato: TextView
        var tvEmailContato: TextView
        var tvNumeroContato: TextView
        var contato: Contato?

        init {
            ivFotoContato = itemView.findViewById(R.id.ivFotoContato)
            tvNomeContato = itemView.findViewById(R.id.tvNomeContato)
            tvEmailContato = itemView.findViewById(R.id.tvEmailContato)
            tvNumeroContato = itemView.findViewById(R.id.tvNumeroContato)
            contato = null

            itemView.setOnClickListener(this)
            itemView.setOnCreateContextMenuListener(this);
        }

        override fun onClick(v: View) {
            if (listener != null) listener!!.onClick(v, adapterPosition)
        }

        override fun onCreateContextMenu(p0: ContextMenu?, p1: View?, p2: ContextMenu.ContextMenuInfo?) {
            var itemTel = p0!!.add(Menu.NONE, 1, 1, R.string.call_menu)
            var intentTel = Intent(Intent.ACTION_VIEW)
            intentTel.data = Uri.parse("tel:" + contato!!.telefone)
            itemTel.intent = intentTel

            var itemSMS = p0!!.add(Menu.NONE, 2, 2, R.string.sms_menu)
            var intentSMS = Intent(Intent.ACTION_VIEW)
            intentSMS.data = Uri.parse("sms:" + contato!!.telefone)
            itemSMS.intent = intentSMS

            var itemMapa = p0!!.add(Menu.NONE, 3, 3, R.string.maps_menu)
            val i = Intent(context, MapActivity::class.java)
            i.putExtra("ENDERECO", contato!!.endereco)
            itemMapa.intent = i

            p0!!.add(Menu.NONE, 4, 4, R.string.share_menu)

            p0!!.add(Menu.NONE, 5, 5, R.string.edit_menu)

            p0!!.add(Menu.NONE, 6, 6, R.string.del_menu)
        }

    }

}