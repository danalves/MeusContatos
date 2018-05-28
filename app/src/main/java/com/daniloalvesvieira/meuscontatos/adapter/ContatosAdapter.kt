package com.daniloalvesvieira.meuscontatos.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.daniloalvesvieira.meuscontatos.model.Contato

import android.R.attr.onClick
import android.content.Intent
import android.net.Uri
import android.view.*
import android.widget.ImageView
import com.daniloalvesvieira.meuscontatos.R.id.ivLogo
import android.widget.TextView
import com.daniloalvesvieira.meuscontatos.MapActivity
import com.daniloalvesvieira.meuscontatos.R
import com.daniloalvesvieira.meuscontatos.listener.OnItemClickListener
import android.widget.AdapterView.AdapterContextMenuInfo




class ContatosAdapter(_context: Context,_contatos: List<Contato>) : RecyclerView.Adapter<ContatosAdapter.ContatoItemViewHolder>() {

    private var context: Context
    private var layoutInflater: LayoutInflater
    private var contatos: List<Contato>? = null
    private var listener: OnItemClickListener? = null

    init{
        context = _context
        layoutInflater = LayoutInflater.from(context)
        contatos = _contatos
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

//        Picasso.with(context).load(android.getUrlImagem())
//                .error(R.drawable.cancel)
//                .placeholder(R.drawable.loading)
//                .into(holder.ivLogo)

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
                                                        View.OnCreateContextMenuListener {

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
            itemView.setOnCreateContextMenuListener(this); //REGISTER ONCREATE MENU LISTENER
        }

        override fun onClick(v: View) {
            if (listener != null) listener!!.onClick(v, adapterPosition)
        }

        override fun onCreateContextMenu(p0: ContextMenu?, p1: View?, p2: ContextMenu.ContextMenuInfo?) {

            // Get the info on which item was selected
            val info = p2 as AdapterContextMenuInfo

            // Get the Adapter behind your ListView (this assumes you're using
            // a ListActivity; if you're not, you'll have to store the Adapter yourself
            // in some way that can be accessed here.)
            val adapter = getListAdapter()

            // Retrieve the item that was clicked on
            val item = adapter.getItem(info.position)
            var itemTel = p0!!.add(Menu.NONE, 1, 1, "Ligar")
            var intentTel = Intent(Intent.ACTION_VIEW)
            intentTel.data = Uri.parse("tel:" + contato!!.telefone)
            itemTel.intent = intentTel

            var itemSMS = p0!!.add(Menu.NONE, 2, 2, "Enviar SMS")
            var intentSMS = Intent(Intent.ACTION_VIEW)
            intentSMS.data = Uri.parse("sms:" + contato!!.telefone)
            itemSMS.intent = intentSMS

            var itemMapa = p0!!.add(Menu.NONE, 3, 3, "Ver no Mapa")
            val i = Intent(context, MapActivity::class.java)
            i.putExtra("ENDERECO", contato!!.endereco)
            itemMapa.intent = i

        }

    }

}