package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.ui.anucio

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.R

class anuncio : Fragment() {

    companion object {
        fun newInstance() = anuncio()
    }

    private val viewModel: AnuncioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_anuncio, container, false)
    }
}