package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.ui.principalAnuncios

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.R
import android.widget.ArrayAdapter;

class principalFragment : Fragment() {


    companion object {
        fun newInstance() = principalFragment()
    }

    private val viewModel: PrincipalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_principal, container, false)


    }
}