package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.ui.PrincipalAnuncios


import  android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrecio: TextView
        var itemUbicacion: TextView
        var itemDimesion: TextView

        init {
            itemImage = itemView.findViewById()
        }
    }
}