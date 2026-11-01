package com.example.ciudadalerta.ui.pantallas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.ciudadalerta.modelos.incidenciasEjemplo

@Composable
fun AppPantallas() {
    // Si es null se ve la lista; si tiene un id, se ve el detalle de esa incidencia
    var seleccionadaId by rememberSaveable { mutableStateOf<Int?>(null) }
    val seleccionada = incidenciasEjemplo.find { it.id == seleccionadaId }

    if (seleccionada == null) {
        PantallaLista(
            incidencias = incidenciasEjemplo,
            onIncidenciaClick = { seleccionadaId = it.id }
        )
    } else {
        PantallaDetalle(
            incidencia = seleccionada,
            // esta lambad serve para que al pulsar el boton de volver
            // y se actualiza la pestaña pues se sale de la pestaña de detalles
            onVolver = { seleccionadaId = null }
        )
    }
}