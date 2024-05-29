package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data

import java.time.LocalDate

data class Cita(
    val idCita: Int,
    val idInmueble: Int?,
    val idEstadoCita: Int?,
    val idUsuarioComprador: Int?,
    val idUsuarioVendedor: Int?,
    val fechaCita: LocalDate?,
    val fechaCreacion: LocalDate?,
    val fum: LocalDate?,
    val ultimoUsuario: Int?,
    val estaCancelada: Char?,
    val fechaCancelacion: LocalDate?,
    val motivoCancelacion: String?,
    val usuarioCancelacion: Int?
)