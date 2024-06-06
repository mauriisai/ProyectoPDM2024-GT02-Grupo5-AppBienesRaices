package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.ui.PrincipalAnuncios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.CustomAdapter
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.R
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data.DatabaseHelper
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data.InmuebleConImagen
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.databinding.FragmentPrincipalAnunciosBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PrincipalAnuncios : Fragment() {
    private var _binding: FragmentPrincipalAnunciosBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapterDepartamentos: ArrayAdapter<String>

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val crearAnuncioViewModel = ViewModelProvider(this).get(PrincipalAnunciosViewModel::class.java)
        _binding = FragmentPrincipalAnunciosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)

        dbHelper = DatabaseHelper(requireContext())
        val inmueblesConImagen: List<InmuebleConImagen> = dbHelper.getInmueblesConImagenCorrelativoUno()

        if (inmueblesConImagen.isEmpty()) {
            // Mostrar un mensaje indicando que no hay inmuebles disponibles y sugerencia
            Toast.makeText(requireContext(), "No hay inmuebles disponibles. Si eres vendedor ve a la pestaña 'Crear Anuncio' para agregar nuevos inmuebles.", Toast.LENGTH_LONG).show()
        } else {
            // Configurar el RecyclerView solo si hay inmuebles disponibles
            val adapter = CustomAdapter(inmueblesConImagen)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter
        }

        LlenarSpinnersDepartamentos()
    }

    private fun LlenarSpinnersDepartamentos() {
        val departamentos = dbHelper.getAllDepartamentos().map { it.nombre }
        adapterDepartamentos = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, departamentos)
        adapterDepartamentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerUbicaciones.adapter = adapterDepartamentos
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PrincipalAnuncios().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
