package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.ui.crearAnuncio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.databinding.FragmentCrearAnuncioBinding

class CrearAnuncioFragment : Fragment() {

    private var _binding: FragmentCrearAnuncioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val crearAnuncioViewModel =
            ViewModelProvider(this).get(CrearAnuncioViewModel::class.java)

        _binding = FragmentCrearAnuncioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        crearAnuncioViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}