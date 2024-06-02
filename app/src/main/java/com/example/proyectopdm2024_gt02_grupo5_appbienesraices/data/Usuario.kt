package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data

import java.time.LocalDate

data class Usuario(
    val idUsuario: Int? = null,
    val usuario: String?,
    val clave: String?,
    val nombre: String?,
    val direccion: String?,
    val telefono1: Int?,
    val telefono2: Int?,
    val correo: String?,
    val idRol: Int?,
    val estadoUsuario: String?,
    val fechaCreacion: LocalDate?,
    val fumUsuario: LocalDate?


)
