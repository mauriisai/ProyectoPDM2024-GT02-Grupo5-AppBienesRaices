package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.ui.PrincipalAnuncios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.CustomAdapter
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.R
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data.DatabaseHelper
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.databinding.FragmentCrearAnuncioBinding
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.databinding.FragmentPrincipalAnunciosBinding
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.ui.crearAnuncio.CrearAnuncioViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PrincipalAnuncios.newInstance] factory method to
 * create an instance of this fragment.
 */
class PrincipalAnuncios : Fragment() {
    private var _binding: FragmentPrincipalAnunciosBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapterDepartamentos: ArrayAdapter<String>


    // TODO: Rename and change types of parameters
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment
        val crearAnuncioViewModel =
            ViewModelProvider(this).get(PrincipalAnunciosViewModel::class.java)
        _binding = FragmentPrincipalAnunciosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter()
        recycler.layoutManager = LinearLayoutManager(requireContext())  // Use requireContext() here
        recycler.adapter = adapter

        dbHelper = DatabaseHelper(requireContext())

        LlenarSpinnersDepartamentos()

    }

    private fun LlenarSpinnersDepartamentos() {
        val departamentos = dbHelper.getAllDepartamentos().map { it.nombre }
        adapterDepartamentos = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, departamentos)
        adapterDepartamentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerUbicaciones.adapter = adapterDepartamentos
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PrincipalAnuncios.
         */
        // TODO: Rename and change types and number of parameters
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