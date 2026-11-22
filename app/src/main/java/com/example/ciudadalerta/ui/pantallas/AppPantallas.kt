package com.example.ciudadalerta.ui.pantallas

import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.example.ciudadalerta.ui.viewmodel.IncidenciasViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ciudadalerta.ui.componentes.AddEditIncidenciaDialog
import com.example.ciudadalerta.ui.componentes.ConfirmDeleteDialog

@Composable
fun AppPantallas(
    viewModel: IncidenciasViewModel = viewModel()
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }

    // Si es null se ve la lista si tiene un id, se ve el detalle de esa incidencia
    val seleccionada = uiState.listaIncidencias.find { it.id == uiState.idSeleccionado }

    // boton Atras: desde el detalle vuelve a la lista
    BackHandler(enabled = seleccionada != null) {
        viewModel.onBackToList()
    }

    // mensajes del Snackbar
    LaunchedEffect(uiState.mensaje) {
        uiState.mensaje?.let { mensaje ->
            snackbarHostState.showSnackbar(mensaje)
            viewModel.onUserMessageShown()
        }
    }

    // navegacion con estado
    if (seleccionada == null) {
        PantallaLista(
            incidencias = uiState.listaIncidencias,
            snackbarHostState = snackbarHostState,
            onIncidenciaClick = {  viewModel.onIncidenciaClicked(it) },
            onEditClick = { viewModel.onEditClicked(it) },
            onDeleteClick = { viewModel.onDeleteClicked(it) },
            onAddClick = {viewModel.onAddClicked()}
        )
    } else {
        PantallaDetalle(
            incidencia = seleccionada,
            snackbarHostState = snackbarHostState,
            onVolver = { viewModel.onBackToList() },
            onEditar = { viewModel.onEditClicked(seleccionada) },
            onBorrar = { viewModel.onDeleteClicked(seleccionada) }
        )
    }

    // dialogos: se dibujan encima de la pantalla visible
    uiState.incidenciaABorrar?.let { incidencia ->
        ConfirmDeleteDialog(
            incidencia = incidencia,
            onCancelar = { viewModel.onDismissDeleteDialog() },
            onConfirmar = { viewModel.onConfirmDelete() }
        )
    }

    if (uiState.isAddDialogOpen) {
        AddEditIncidenciaDialog(
            onCancelar = { viewModel.onDismissAddDialog() },
            onConfirmar = { titulo, categoria, descripcion, direccion ->
                viewModel.onInsertIncidencia(titulo, categoria, descripcion, direccion)
            }
        )
    }

    uiState.incidenciaAEditar?.let { incidencia ->
        AddEditIncidenciaDialog(
            incidenciaToEdit = incidencia,
            onCancelar = { viewModel.onDismissEditDialog() },
            onConfirmar = { titulo, categoria, descripcion, direccion ->
                viewModel.onUpdateIncidencia(incidencia.id, titulo, categoria, descripcion, direccion)
            }
        )
    }
}