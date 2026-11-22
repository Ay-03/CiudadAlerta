package com.example.ciudadalerta.ui.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ciudadalerta.modelos.Categoria
import com.example.ciudadalerta.modelos.Incidencia
import com.example.ciudadalerta.datos.datosEjemplo
import com.example.ciudadalerta.modelos.Estado
import com.example.ciudadalerta.ui.componentes.Etiqueta
import com.example.ciudadalerta.ui.componentes.FotoIncidencia
import com.example.ciudadalerta.ui.theme.CiudadAlertaTheme

@Composable
fun PantallaLista(
    incidencias: List<Incidencia>,
    snackbarHostState: SnackbarHostState,
    onIncidenciaClick: (Incidencia) -> Unit,
    onEditClick: (Incidencia) -> Unit,
    onDeleteClick: (Incidencia) -> Unit,
    onAddClick: () -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = Color(0xFFFF9800)
                ) {
                Icon(Icons.Default.Add,
                    contentDescription = "Añadir incidencia")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Nombre de la aplicacion mas trde ponre el menu
            Text(
                text = "CiudadAlerta",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Saludo y etiqueta "X activas"
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("Tu actividad", style = MaterialTheme.typography.bodySmall)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mis incidencias",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    // cuenta las que no estan resueltas
                    Etiqueta("${incidencias.count { it.estado != Estado.RESUELTA }} activas")
                }
            }

            // Tipos de incidencia
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listOf("Todas") + Categoria.entries.map { it.etiqueta }) { texto ->
                    OutlinedButton(onClick = { }) {
                        Text(texto)
                    }
                }
            }

            // lista vacia
            if (incidencias.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No tienes incidencias. Pulsa + para añadir una.")
                }
            } else {

                    // Lista de tarjetas
                    LazyColumn(
                        // 80.dp abajo para que el botón + no tape la ultima tarjeta
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 80.dp),                    verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(items = incidencias, key = { it.id }) { incidencia ->
                            TarjetaIncidencia(
                                incidencia = incidencia,
                                onClick = { onIncidenciaClick(incidencia) },
                                onEditClick = { onEditClick(incidencia) },
                                onDeleteClick = { onDeleteClick(incidencia) }
                            )
                        }
                    }
            }
        }
    }

}

@Composable
fun TarjetaIncidencia(
    incidencia: Incidencia,
    onClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            // Imagen a la izquierda
            FotoIncidencia(
                incidencia = incidencia,
                modifier = Modifier
                    .width(110.dp)
                    .fillMaxHeight()
            )

            // Texto a la derecha
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = incidencia.categoria.etiqueta,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                    Etiqueta(incidencia.estado.etiqueta)
                }
                Text(
                    text = incidencia.titulo,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = incidencia.fechaHora,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(onClick = onEditClick, modifier = Modifier.size(32.dp)) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Editar incidencia"
                        )
                    }
                    IconButton(onClick = onDeleteClick, modifier = Modifier.size(32.dp)) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Eliminar incidencia"
                        )
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaListaPreview() {
    CiudadAlertaTheme {
        PantallaLista(
            incidencias = datosEjemplo,
            snackbarHostState = SnackbarHostState() ,
            onIncidenciaClick = { },
            onEditClick = {  },
            onDeleteClick = { },
            onAddClick = {}
        )
    }
}