package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.ui.crearAnuncio

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.R

class ImagesAdapter(private val imageList: List<Uri>) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUri = imageList[position]
        Glide.with(holder.imageView.context).load(imageUri).into(holder.imageView)
    }


    override fun getItemCount(): Int {
        return imageList.size
    }
}
