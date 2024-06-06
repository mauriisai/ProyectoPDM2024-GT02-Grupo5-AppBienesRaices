package com.example.proyectopdm2024_gt02_grupo5_appbienesraices
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.R
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data.InmuebleConImagen

class CustomAdapter(private val data: List<InmuebleConImagen>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.tittle)
        val precioTextView: TextView = itemView.findViewById(R.id.textPrecio)
        val ubicacionTextView: TextView = itemView.findViewById(R.id.textUbicacion)
        val dimensionesTextView: TextView = itemView.findViewById(R.id.textDimensiones)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]

        Glide.with(holder.itemView.context).load(currentItem.imagen).into(holder.imageView)
        holder.titleTextView.text = currentItem.titulo
        holder.precioTextView.text = "Precio: ${currentItem.precio}"
        holder.ubicacionTextView.text = "Ubicación: ${currentItem.ubicacion}"
        holder.dimensionesTextView.text = "Tamaño: ${currentItem.tamanio} m²"
    }

    override fun getItemCount() = data.size
}
