package com.example.ciudadalerta.ui.viewmodel

import com.example.ciudadalerta.modelos.Incidencia

data class IncidenciasUiState(
    val listaIncidencias: List<Incidencia> = emptyList(),
    val idSeleccionado: Int? = null,   // null = vuelve a la lista
    val incidenciaAEditar: Incidencia? = null, // dialogo de edicion
    val incidenciaABorrar: Incidencia? = null, // dialogo de borrado
    val isAddDialogOpen: Boolean = false,    // dialogo de alta
    val mensaje: String? = null          // mensaje del Snackbar
)