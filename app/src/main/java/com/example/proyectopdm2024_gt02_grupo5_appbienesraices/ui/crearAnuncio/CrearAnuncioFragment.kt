package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.ui.crearAnuncio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data.DatabaseHelper
import com.example.proyectopdm2024_gt02_grupo5_appbienesraices.databinding.FragmentCrearAnuncioBinding

class CrearAnuncioFragment : Fragment() {

    private var _binding: FragmentCrearAnuncioBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapterDepartamentos: ArrayAdapter<String>
    private lateinit var adapterMunicipios: ArrayAdapter<String>
    private lateinit var adapterTipoInmueble: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val crearAnuncioViewModel =
            ViewModelProvider(this).get(CrearAnuncioViewModel::class.java)

        _binding = FragmentCrearAnuncioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DatabaseHelper(requireContext().applicationContext)

        // Llenar tablas Catalogos con datos iniciales
        // llenarTablaTiposInmueble()

        //llenarTablaDepartamentos()
        //llenarTablaMunicipios()


        // Llamada metodos de llenado de Spinners
        llenarSpinnerTipoInmueble()
        llenarSpinnersDepartamentosYMunicipios()

    }

    private fun llenarSpinnersDepartamentosYMunicipios() {
        // Obtener la lista de departamentos, municipios y tiposInmuebles
        val departamentos = dbHelper.getAllDepartamentos().map { it.nombre }
        val municipios = mutableListOf<String>()

        // Configurar el adaptador para el Spinner de departamentos
        adapterDepartamentos = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, departamentos)
        adapterDepartamentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDepartamentos.adapter = adapterDepartamentos

        // Configurar el adaptador para el Spinner de municipios
        adapterMunicipios = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, municipios)
        adapterMunicipios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMunicipios.adapter = adapterMunicipios

        // Manejar la selección de departamento para actualizar la lista de municipios
        binding.spinnerDepartamentos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val departamentoId = position + 1 // Suponiendo que los IDs de departamento comienzan en 1 y son consecutivos
                municipios.clear()
                municipios.addAll(dbHelper.getMunicipiosByDepartamento(departamentoId).map { it.nombre })
                adapterMunicipios.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada
            }
        }
    }

    private fun llenarSpinnerTipoInmueble() {
        // Obtener los datos de la tabla
        val tiposInmueble = dbHelper.getAllTipoInmueble().map { it.tipo }

        // Crear un ArrayAdapter para el Spinner
        adapterTipoInmueble = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tiposInmueble)
        adapterTipoInmueble.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTipoInmueble.adapter = adapterTipoInmueble
    }

    private fun llenarTablaDepartamentos() {
        dbHelper.insertDepartamento(1, "Ahuachapán")
        dbHelper.insertDepartamento(2, "Cabañas")
        dbHelper.insertDepartamento(3, "Chalatenango")
        dbHelper.insertDepartamento(4, "Cuscatlán")
        dbHelper.insertDepartamento(5, "La Libertad")
        dbHelper.insertDepartamento(6, "La Paz")
        dbHelper.insertDepartamento(7, "La Unión")
        dbHelper.insertDepartamento(8, "Morazán")
        dbHelper.insertDepartamento(9, "San Miguel")
        dbHelper.insertDepartamento(10, "San Salvador")
        dbHelper.insertDepartamento(11, "San Vicente")
        dbHelper.insertDepartamento(12, "Santa Ana")
        dbHelper.insertDepartamento(13, "Sonsonate")
        dbHelper.insertDepartamento(14, "Usulután")
    }

    private fun llenarTablaTiposInmueble() {
        dbHelper.insertTipoInmueble(1, "Terreno")
        dbHelper.insertTipoInmueble(2, "Apartamento")
        dbHelper.insertTipoInmueble(3, "Casa")
        dbHelper.insertTipoInmueble(4, "Edificio Industrial")
        dbHelper.insertTipoInmueble(5, "Rancho de playa")
    }

    private fun llenarTablaMunicipios() {
        val municipios = listOf(
            Triple(1, 1, "Ahuachapán"),
            Triple(2, 1, "Jujutla"),
            Triple(3, 1, "Atiquizaya"),
            Triple(4, 1, "Concepción de Ataco"),
            Triple(5, 1, "El Refugio"),
            Triple(6, 1, "Guaymango"),
            Triple(7, 1, "Apaneca"),
            Triple(8, 1, "San Francisco Menéndez"),
            Triple(9, 1, "San Lorenzo"),
            Triple(10, 1, "San Pedro Puxtla"),
            Triple(11, 1, "Tacuba"),
            Triple(12, 1, "Turín"),
            Triple(13, 2, "Cinquera"),
            Triple(14, 2, "Villa Dolores"),
            Triple(15, 2, "Guacotecti"),
            Triple(16, 2, "Ilobasco"),
            Triple(17, 2, "Jutiapa"),
            Triple(18, 2, "San Isidro"),
            Triple(19, 2, "Sensuntepeque"),
            Triple(20, 2, "Ciudad de Tejutepeque"),
            Triple(21, 2, "Victoria"),
            Triple(22, 3, "Agua Caliente"),
            Triple(23, 3, "Arcatao"),
            Triple(24, 3, "Azacualpa"),
            Triple(25, 3, "Chalatenango"),
            Triple(26, 3, "Citalá"),
            Triple(27, 3, "Comalapa"),
            Triple(28, 3, "Concepción Quezaltepeque"),
            Triple(29, 3, "Dulce Nombre de María"),
            Triple(30, 3, "El Carrizal"),
            Triple(31, 3, "El Paraíso"),
            Triple(32, 3, "La Laguna"),
            Triple(33, 3, "La Palma"),
            Triple(34, 3, "La Reina"),
            Triple(35, 3, "Las Vueltas"),
            Triple(36, 3, "Nombre de Jesús"),
            Triple(37, 3, "Nueva Concepción"),
            Triple(38, 3, "Nueva Trinidad"),
            Triple(39, 3, "Ojos de Agua"),
            Triple(40, 3, "Potonico"),
            Triple(41, 3, "San Antonio de la Cruz"),
            Triple(42, 3, "San Antonio Los Ranchos"),
            Triple(43, 3, "San Fernando"),
            Triple(44, 3, "San Francisco Lempa"),
            Triple(45, 3, "San Francisco Morazán"),
            Triple(46, 3, "San Ignacio"),
            Triple(47, 3, "San Isidro Labrador"),
            Triple(48, 3, "San José Cancasque"),
            Triple(49, 3, "San José Las Flores"),
            Triple(50, 3, "San Luis del Carmen"),
            Triple(51, 3, "San Miguel de Mercedes"),
            Triple(52, 3, "San Rafael"),
            Triple(53, 3, "Santa Rita"),
            Triple(54, 3, "Tejutla"),
            Triple(55, 4, "Candelaria"),
            Triple(56, 4, "Cojutepeque"),
            Triple(57, 4, "El Carmen"),
            Triple(58, 4, "El Rosario"),
            Triple(59, 4, "Monte San Juan"),
            Triple(60, 4, "Oratorio de Concepción"),
            Triple(61, 4, "San Bartolomé Perulapía"),
            Triple(62, 4, "San Cristóbal"),
            Triple(63, 4, "San José Guayabal"),
            Triple(64, 4, "San Pedro Perulapán"),
            Triple(65, 4, "San Rafael Cedros"),
            Triple(66, 4, "San Ramón"),
            Triple(67, 4, "Santa Cruz Analquito"),
            Triple(68, 4, "Santa Cruz Michapa"),
            Triple(69, 4, "Suchitoto"),
            Triple(70, 4, "Tenancingo"),
            Triple(71, 5, "Antiguo Cuscatlán"),
            Triple(72, 5, "Chiltiupán"),
            Triple(73, 5, "Ciudad Arce"),
            Triple(74, 5, "Colón"),
            Triple(75, 5, "Comasagua"),
            Triple(76, 5, "Huizúcar"),
            Triple(77, 5, "Jayaque"),
            Triple(78, 5, "Jicalapa"),
            Triple(79, 5, "La Libertad"),
            Triple(80, 5, "Nueva San Salvador"),
            Triple(81, 5, "Nuevo Cuscatlán"),
            Triple(82, 5, "Opico"),
            Triple(83, 5, "Quezaltepeque"),
            Triple(84, 5, "Sacacoyo"),
            Triple(85, 5, "San José Villanueva"),
            Triple(86, 5, "San Matías"),
            Triple(87, 5, "San Pablo Tacachico"),
            Triple(88, 5, "Talnique"),
            Triple(89, 5, "Tamanique"),
            Triple(90, 5, "Teotepeque"),
            Triple(91, 5, "Tepecoyo"),
            Triple(92, 5, "Zaragoza"),
            Triple(93, 6, "Cuyultitán"),
            Triple(94, 6, "El Rosario"),
            Triple(95, 6, "Jerusalén"),
            Triple(96, 6, "Mercedes La Ceiba"),
            Triple(97, 6, "Olocuilta"),
            Triple(98, 6, "Paraíso de Osorio"),
            Triple(99, 6, "San Antonio Masahuat"),
            Triple(100, 6, "San Emigdio"),
            Triple(101, 6, "San Francisco Chinameca"),
            Triple(102, 6, "San Juan Nonualco"),
            Triple(103, 6, "San Juan Talpa"),
            Triple(104, 6, "San Juan Tepezontes"),
            Triple(105, 6, "San Luis La Herradura"),
            Triple(106, 6, "San Luis Talpa"),
            Triple(107, 6, "San Miguel Tepezontes"),
            Triple(108, 6, "San Pedro Masahuat"),
            Triple(109, 6, "San Pedro Nonualco"),
            Triple(110, 6, "San Rafael Obrajuelo"),
            Triple(111, 6, "Santa María Ostuma"),
            Triple(112, 6, "Santiago Nonualco"),
            Triple(113, 6, "Tapalhuaca"),
            Triple(114, 6, "Zacatecoluca"),
            Triple(115, 7, "Anamorós"),
            Triple(116, 7, "Bolívar"),
            Triple(117, 7, "Concepción de Oriente"),
            Triple(118, 7, "Conchagua"),
            Triple(119, 7, "El Carmen"),
            Triple(120, 7, "El Sauce"),
            Triple(121, 7, "Intipucá"),
            Triple(122, 7, "La Unión"),
            Triple(123, 7, "Lislique"),
            Triple(124, 7, "Meanguera del Golfo"),
            Triple(125, 7, "Nueva Esparta"),
            Triple(126, 7, "Pasaquina"),
            Triple(127, 7, "Polorós"),
            Triple(128, 7, "San Alejo"),
            Triple(129, 7, "San José"),
            Triple(130, 7, "Santa Rosa de Lima"),
            Triple(131, 7, "Yayantique"),
            Triple(132, 7, "Yucuayquín"),
            Triple(133, 8, "Arambala"),
            Triple(134, 8, "Cacaopera"),
            Triple(135, 8, "Chilanga"),
            Triple(136, 8, "Corinto"),
            Triple(137, 8, "Delicias de Concepción"),
            Triple(138, 8, "El Divisadero"),
            Triple(139, 8, "El Rosario"),
            Triple(140, 8, "Gualococti"),
            Triple(141, 8, "Guatajiagua"),
            Triple(142, 8, "Joateca"),
            Triple(143, 8, "Jocoaitique"),
            Triple(144, 8, "Jocoro"),
            Triple(145, 8, "Lolotiquillo"),
            Triple(146, 8, "Meanguera"),
            Triple(147, 8, "Osicala"),
            Triple(148, 8, "Perquín"),
            Triple(149, 8, "San Carlos"),
            Triple(150, 8, "San Fernando"),
            Triple(151, 8, "San Francisco Gotera"),
            Triple(152, 8, "San Isidro"),
            Triple(153, 8, "San Simón"),
            Triple(154, 8, "Sensembra"),
            Triple(155, 8, "Sociedad"),
            Triple(156, 8, "Torola"),
            Triple(157, 8, "Yamabal"),
            Triple(158, 8, "Yoloaiquín"),
            Triple(159, 9, "Carolina"),
            Triple(160, 9, "Chapeltique"),
            Triple(161, 9, "Chinameca"),
            Triple(162, 9, "Chirilagua"),
            Triple(163, 9, "Ciudad Barrios"),
            Triple(164, 9, "Comacarán"),
            Triple(165, 9, "El Tránsito"),
            Triple(166, 9, "Lolotique"),
            Triple(167, 9, "Moncagua"),
            Triple(168, 9, "Nueva Guadalupe"),
            Triple(169, 9, "Nuevo Edén de San Juan"),
            Triple(170, 9, "Quelepa"),
            Triple(171, 9, "San Antonio"),
            Triple(172, 9, "San Gerardo"),
            Triple(173, 9, "San Jorge"),
            Triple(174, 9, "San Luis de la Reina"),
            Triple(175, 9, "San Miguel"),
            Triple(176, 9, "San Rafael"),
            Triple(177, 9, "Sesori"),
            Triple(178, 9, "Uluazapa"),
            Triple(179, 10, "Aguilares"),
            Triple(180, 10, "Apopa"),
            Triple(181, 10, "Ayutuxtepeque"),
            Triple(182, 10, "Cuscatancingo"),
            Triple(183, 10, "Delgado"),
            Triple(184, 10, "El Paisnal"),
            Triple(185, 10, "Guazapa"),
            Triple(186, 10, "Ilopango"),
            Triple(187, 10, "Mejicanos"),
            Triple(188, 10, "Nejapa"),
            Triple(189, 10, "Panchimalco"),
            Triple(190, 10, "Rosario de Mora"),
            Triple(191, 10, "San Marcos"),
            Triple(192, 10, "San Martín"),
            Triple(193, 10, "San Salvador"),
            Triple(194, 10, "Santiago Texacuangos"),
            Triple(195, 10, "Santo Tomás"),
            Triple(196, 10, "Soyapango"),
            Triple(197, 10, "Tonacatepeque"),
            Triple(198, 11, "Apastepeque"),
            Triple(199, 11, "Guadalupe"),
            Triple(200, 11, "San Cayetano Istepeque"),
            Triple(201, 11, "San Esteban Catarina"),
            Triple(202, 11, "San Ildefonso"),
            Triple(203, 11, "San Lorenzo"),
            Triple(204, 11, "San Sebastián"),
            Triple(205, 11, "Santa Clara"),
            Triple(206, 11, "Santo Domingo"),
            Triple(207, 11, "San Vicente"),
            Triple(208, 11, "Tecoluca"),
            Triple(209, 11, "Tepetitán"),
            Triple(210, 11, "Verapaz"),
            Triple(211, 12, "Candelaria de la Frontera"),
            Triple(212, 12, "Chalchuapa"),
            Triple(213, 12, "Coatepeque"),
            Triple(214, 12, "El Congo"),
            Triple(215, 12, "El Porvenir"),
            Triple(216, 12, "Masahuat"),
            Triple(217, 12, "Metapán"),
            Triple(218, 12, "San Antonio Pajonal"),
            Triple(219, 12, "San Sebastián Salitrillo"),
            Triple(220, 12, "Santa Ana"),
            Triple(221, 12, "Santa Rosa Guachipilín"),
            Triple(222, 12, "Santiago de la Frontera"),
            Triple(223, 12, "Texistepeque"),
            Triple(224, 13, "Acajutla"),
            Triple(225, 13, "Armenia"),
            Triple(226, 13, "Caluco"),
            Triple(227, 13, "Cuisnahuat"),
            Triple(228, 13, "Izalco"),
            Triple(229, 13, "Juayúa"),
            Triple(230, 13, "Nahuizalco"),
            Triple(231, 13, "Nahulingo"),
            Triple(232, 13, "Salcoatitán"),
            Triple(233, 13, "San Antonio del Monte"),
            Triple(234, 13, "San Julián"),
            Triple(235, 13, "Santa Catarina Masahuat"),
            Triple(236, 13, "Santa Isabel Ishuatán"),
            Triple(237, 13, "Santo Domingo"),
            Triple(238, 13, "Sonsonate"),
            Triple(239, 13, "Sonzacate"),
            Triple(240, 14, "Alegría"),
            Triple(241, 14, "Berlín"),
            Triple(242, 14, "California"),
            Triple(243, 14, "Concepción Batres"),
            Triple(244, 14, "El Triunfo"),
            Triple(245, 14, "Ereguayquín"),
            Triple(246, 14, "Estanzuelas"),
            Triple(247, 14, "Jiquilisco"),
            Triple(248, 14, "Jucuapa"),
            Triple(249, 14, "Jucuarán"),
            Triple(250, 14, "Mercedes Umaña"),
            Triple(251, 14, "Nueva Granada"),
            Triple(252, 14, "Ozatlán"),
            Triple(253, 14, "Puerto El Triunfo"),
            Triple(254, 14, "San Agustín"),
            Triple(255, 14, "San Buenaventura"),
            Triple(256, 14, "San Dionisio"),
            Triple(257, 14, "San Francisco Javier"),
            Triple(258, 14, "Santa Elena"),
            Triple(259, 14, "Santa María"),
            Triple(260, 14, "Santiago de María"),
            Triple(261, 14, "Tecapán"),
            Triple(262, 14, "Usulután")
        )

        for (municipio in municipios) {
            dbHelper.insertMunicipio(municipio.first, municipio.third, municipio.second)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
