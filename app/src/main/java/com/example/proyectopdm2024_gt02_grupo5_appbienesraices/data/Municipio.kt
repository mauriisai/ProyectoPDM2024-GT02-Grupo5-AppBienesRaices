package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data

data class Municipio(val id_municipio: Int, val id_departamento: Int, val nombre: String) {
    override fun toString(): String {
        return nombre
    }
}
