package com.example.ciudadalerta.modelos

data class Incidencia(
    val id: Int,
    val titulo: String,
    val categoria: Categoria,
    val descripcion: String,
    val fechaHora: String,
    val estado: Estado,
    val direccion: String,
    val fotoRes: Int? = null
)