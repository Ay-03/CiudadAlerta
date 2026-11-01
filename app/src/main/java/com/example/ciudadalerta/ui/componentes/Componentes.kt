package com.example.ciudadalerta.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ciudadalerta.modelos.Incidencia

// sino no hay foto dibuja cuadro gris
@Composable
fun FotoIncidencia(incidencia: Incidencia, modifier: Modifier = Modifier) {
    if (incidencia.fotoRes != null) {
        Image(
            painter = painterResource(incidencia.fotoRes),
            contentDescription = incidencia.titulo,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    } else {
        Box(
            modifier = modifier.background(MaterialTheme.colorScheme.outline),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sin foto",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.surface
            )
        }
    }
}

// sirve para el estado y para "X activas"
@Composable
fun Etiqueta(texto: String) {
    OutlinedCard(shape = RoundedCornerShape(10.dp)) {
        Text(
            text = texto,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1
        )
    }
}