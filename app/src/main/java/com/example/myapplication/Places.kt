package com.example.myapplication


import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class Places(val imagen:Int, val titulo:String )
class PlacesAdapter(var items: ArrayList<Places>) : RecyclerView.Adapter<PlacesAdapter.TarjViewHolder>() {
    lateinit var onClick : (View) -> Unit
    lateinit var onLongClick : (View) -> Unit
    init {
        this.items = items

    }

    class TarjViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnCreateContextMenuListener {
        var laImagen: ImageView

        var tituloView:TextView
        val cancelBoton: Button
        init {
            laImagen=itemView.findViewById(R.id.imageView)
            cancelBoton=itemView.findViewById(R.id.cancel)
            tituloView=itemView.findViewById(R.id.textViewTitulo)
            itemView.setOnCreateContextMenuListener(this)

        }
        
        fun bindTarjeta(t: Places, onLongClick: (View) -> Unit) = with(itemView) {
            laImagen.setImageResource(t.imagen)

            tituloView.text=t.titulo
            setOnLongClickListener { onLongClick(itemView)
                true }
        }
        override fun onCreateContextMenu(contextMenu: ContextMenu, view: View, contextMenuInfo: ContextMenu.ContextMenuInfo?) {
            contextMenu.add(0, 0, adapterPosition, "Editar")     //groupId, itemId, order, title
            contextMenu.add(0, 1, adapterPosition, "Eliminar")
            contextMenu.add(0, 2, adapterPosition, "Compartir")
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TarjViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.places, viewGroup, false)
        return TarjViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: TarjViewHolder, pos: Int) {
        val item = items.get(pos)
        viewHolder.bindTarjeta(item, onLongClick)
        viewHolder.cancelBoton.setOnClickListener{
            items.removeAt(viewHolder.adapterPosition)
            notifyItemRemoved(viewHolder.adapterPosition)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}