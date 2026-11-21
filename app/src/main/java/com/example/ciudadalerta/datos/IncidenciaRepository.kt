package com.example.ciudadalerta.datos

import com.example.ciudadalerta.modelos.Incidencia

object IncidenciaRepository {
    private val incidencias = datosEjemplo.toMutableList()

    // le sumamos 1 al tamaño de la lista para evitar empezar por 0 o repetir id
    private var siguienteId = datosEjemplo.size + 1

    fun getIncidencias(): List<Incidencia> = incidencias.toList()

    fun getIncidenciaById(id: Int): Incidencia? = incidencias.find { it.id == id }

    fun addIncidencia(incidencia: Incidencia) {
        incidencias.add(incidencia.copy(id = siguienteId)) // el repositorio pone el id
        siguienteId++ // incrementamos el id para la siguiente incidencia
    }

    fun updateIncidencia(incidencia: Incidencia) {
        val index = incidencias.indexOfFirst { it.id == incidencia.id }
        if (index != -1) {
            incidencias[index] = incidencia
        }
    }

    fun deleteIncidencia(id: Int) {
        incidencias.removeAll { it.id == id }
    }
}

