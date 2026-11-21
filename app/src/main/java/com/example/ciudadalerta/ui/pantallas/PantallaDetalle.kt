package com.example.ciudadalerta.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ciudadalerta.datos.datosEjemplo
import com.example.ciudadalerta.modelos.Incidencia
import com.example.ciudadalerta.ui.componentes.Etiqueta
import com.example.ciudadalerta.ui.componentes.FotoIncidencia
import com.example.ciudadalerta.ui.theme.CiudadAlertaTheme

@Composable
fun PantallaDetalle(
    incidencia: Incidencia,
    snackbarHostState: SnackbarHostState,
    onVolver: () -> Unit,
    onEditar: () -> Unit,
    onBorrar: () -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(paddingValues)
        ) {
            // imagen en el centro y boton de volver arriba a la izquierda
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                FotoIncidencia(
                    incidencia = incidencia,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(90.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Button(
                    onClick = onVolver,
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                        .size(48.dp)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = incidencia.categoria.etiqueta,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Etiqueta(incidencia.estado.etiqueta)
                }

                Text(
                    text = incidencia.titulo,
                    style = MaterialTheme.typography.titleLarge
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(Icons.Default.DateRange, contentDescription = null,)
                    Text(
                        text = "Publicado ${incidencia.fechaHora}",
                        style = MaterialTheme.typography.labelMedium
                    )
                }



                // Linea separadora
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(MaterialTheme.colorScheme.outline)
                )

                Text("Descripcion", style = MaterialTheme.typography.titleMedium)
                Text(incidencia.descripcion, style = MaterialTheme.typography.bodyMedium)

                Text("Ubicacion", style = MaterialTheme.typography.titleMedium)

                // mas tarde pondre el mapa aqui
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Mapa proximamente",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
                ) {
                    OutlinedButton(
                        onClick = onEditar
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Editar")
                    }
                    Button(
                        onClick = onBorrar,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError
                        )
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Eliminar")
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PantallaDetallePreview() {
    CiudadAlertaTheme {
        PantallaDetalle(
            incidencia = datosEjemplo.first(),
            onVolver = { } ,
            snackbarHostState = SnackbarHostState(),
            onEditar = {  },
            onBorrar = { }
        )
    }
}