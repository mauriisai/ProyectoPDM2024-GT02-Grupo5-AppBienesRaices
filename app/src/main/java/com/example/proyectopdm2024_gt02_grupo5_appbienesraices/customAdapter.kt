package com.example.proyectopdm2024_gt02_grupo5_appbienesraices

import android.view.LayoutInflater
import  android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    val titles = arrayOf("Artículo1", "Artículo2", "Artículo3", "Artículo4", "Artículo5")

    val precios = arrayOf("Precio1", "Precio2", "Precio3", "Precio4", "Precio5")
    val ubicaciones = arrayOf("Ubicación1", "Ubicación2", "Ubicación3", "Ubicación4", "Ubicación5")
    val dimensiones = arrayOf("Dimensiones1", "Dimensiones2", "Dimensiones3", "Dimensiones4", "Dimensiones5")

    val images = intArrayOf(R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemPrecio.text = precios[i]
        viewHolder.itemUbicacion.text = ubicaciones[i]
        viewHolder.itemDimesion.text = dimensiones[i]
    }
    override fun getItemCount(): Int {
        return titles.size
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrecio: TextView
        var itemUbicacion: TextView
        var itemDimesion: TextView

        init {
            itemImage = itemView.findViewById(R.id.imageView)
            itemTitle = itemView.findViewById(R.id.tittle)
            itemPrecio = itemView.findViewById(R.id.textPrecio)
            itemUbicacion = itemView.findViewById(R.id.textUbicacion)
            itemDimesion = itemView.findViewById(R.id.textDimensiones)
        }
    }
}