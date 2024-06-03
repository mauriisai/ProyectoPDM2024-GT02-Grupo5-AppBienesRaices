package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data

import java.time.LocalDate

data class Inmueble(
    val titulo: String,
    val idDepartamento: Int,
    val idMunicipio: Int?,
    val descripcion: String,
    val precio: Double,
    val idEstado: Int,
    val ubicacion: String?,
    val tamanio: Double?,
    val visitas: Int?,
    val idTipoInmueble: Int?,
    val idUsuarioVendedor: Int?,
    val fechaVenta: LocalDate?,
    val fechaCreacion: LocalDate?,
    val fum: LocalDate?,
    val ultimoUsuario: Int?,
    val usuarioCreacion: Int?
)
