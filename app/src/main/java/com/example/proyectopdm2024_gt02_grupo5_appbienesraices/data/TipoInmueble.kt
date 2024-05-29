package com.example.proyectopdm2024_gt02_grupo5_appbienesraices.data

data class TipoInmueble( val id: Int, val tipo: String ) {
    override fun toString(): String {
        return tipo
    }
}