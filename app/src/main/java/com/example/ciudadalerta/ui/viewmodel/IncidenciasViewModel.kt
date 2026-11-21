package com.example.ciudadalerta.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ciudadalerta.datos.IncidenciaRepository
import com.example.ciudadalerta.modelos.Categoria
import com.example.ciudadalerta.modelos.Estado
import com.example.ciudadalerta.modelos.Incidencia

class IncidenciasViewModel : ViewModel() {
    var uiState by mutableStateOf(IncidenciasUiState())
        private set

    init {
        uiState = uiState.copy(listaIncidencias = IncidenciaRepository.getIncidencias())
    }

    // Navegacion
    fun onIncidenciaClicked(incidencia: Incidencia) {
        uiState = uiState.copy(idSeleccionado = incidencia.id)
    }

    fun onBackToList() {
        uiState = uiState.copy(idSeleccionado = null)
    }

    // Añadir incidencia
    fun onAddClicked() {
        uiState = uiState.copy(isAddDialogOpen = true)
    }

    fun onDismissAddDialog() {
        uiState = uiState.copy(isAddDialogOpen = false)
    }

    fun onInsertIncidencia(titulo: String, categoria: Categoria, descripcion: String, direccion: String) {
        val nueva = Incidencia(
            id = 0, // el repositorio le asigna el id real
            titulo = titulo,
            categoria = categoria,
            descripcion = descripcion,
            fechaHora = "Ahora mismo",
            estado = Estado.PENDIENTE,
            direccion = direccion
        )
        IncidenciaRepository.addIncidencia(nueva)
        uiState = uiState.copy(
            listaIncidencias = IncidenciaRepository.getIncidencias(),
            isAddDialogOpen = false,
            mensaje = "Incidencia '$titulo' añadida correctamente"
        )
    }

    // Editar una incidencia
    fun onEditClicked(incidencia: Incidencia) {
        uiState = uiState.copy(incidenciaAEditar = incidencia)
    }

    fun onDismissEditDialog() {
        uiState = uiState.copy(incidenciaAEditar = null)
    }

    fun onUpdateIncidencia(id: Int, titulo: String, categoria: Categoria, descripcion: String, direccion: String) {
        val existente = IncidenciaRepository.getIncidenciaById(id) ?: return
        val actualizada = existente.copy(
            titulo = titulo,
            categoria = categoria,
            descripcion = descripcion,
            direccion = direccion
        )
        IncidenciaRepository.updateIncidencia(actualizada)
        uiState = uiState.copy(
            listaIncidencias = IncidenciaRepository.getIncidencias(),
            incidenciaAEditar = null,
            mensaje = "Incidencia actualizada"
        )
    }

    // Borrar una incidencia
    fun onDeleteClicked(incidencia: Incidencia) {
        uiState = uiState.copy(incidenciaABorrar = incidencia)
    }

    fun onDismissDeleteDialog() {
        uiState = uiState.copy(incidenciaABorrar = null)
    }

    // Confirmar borrado y cambiar idselciondaod
    fun onConfirmDelete() {
        val objetivo = uiState.incidenciaABorrar ?: return
        IncidenciaRepository.deleteIncidencia(objetivo.id)
        uiState = uiState.copy(
            listaIncidencias = IncidenciaRepository.getIncidencias(),
            incidenciaABorrar = null,
            idSeleccionado = null, // vuelve a la lista
            mensaje = "Incidencia '${objetivo.titulo}' borrada"
        )
    }

    // Mensajes
    fun onUserMessageShown() {
        uiState = uiState.copy(mensaje = null)
    }
}