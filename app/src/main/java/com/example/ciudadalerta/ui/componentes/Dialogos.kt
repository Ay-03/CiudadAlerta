package com.example.ciudadalerta.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.ciudadalerta.modelos.Categoria
import com.example.ciudadalerta.modelos.Incidencia

// Campo de texto con mensaje de error debajo (UD3 7.4)
@Composable
fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    error: String? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        isError = error != null,
        supportingText = if (error != null) {
            { Text(error) }
        } else {
            null
        },
        modifier = Modifier.fillMaxWidth()
    )
}

// Dialogo de confirmacion de borrado
@Composable
fun ConfirmDeleteDialog(
    incidencia: Incidencia,
    onCancelar: () -> Unit,
    onConfirmar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text("Eliminar incidencia") },
        text = { Text("¿Seguro que quieres eliminar '${incidencia.titulo}'?") },
        confirmButton = {
            TextButton(
                onClick = onConfirmar,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}

// Un solo dialogo para crear y editar
@Composable
fun AddEditIncidenciaDialog(
    incidenciaToEdit: Incidencia? = null,
    onCancelar: () -> Unit,
    onConfirmar: (titulo: String, categoria: Categoria, descripcion: String, direccion: String) -> Unit
) {
    // estado local del formulario: si se edita, empieza con los datos de la incidencia
    var titulo by remember { mutableStateOf(incidenciaToEdit?.titulo ?: "") }
    var categoria by remember { mutableStateOf(incidenciaToEdit?.categoria ?: Categoria.BACHES) }
    var descripcion by remember { mutableStateOf(incidenciaToEdit?.descripcion ?: "") }
    var direccion by remember { mutableStateOf(incidenciaToEdit?.direccion ?: "") }
    var showErrors by remember { mutableStateOf(false) }

    // validacion: null si el campo esta bien
    val tituloError = if (titulo.isBlank()) "El titulo es obligatorio" else null
    val descripcionError = if (descripcion.isBlank()) "La descripcion es obligatoria" else null
    val direccionError = if (direccion.isBlank()) "La direccion es obligatoria" else null
    val isFormValid = tituloError == null && descripcionError == null && direccionError == null

    AlertDialog(
        onDismissRequest = onCancelar,
        title = {
            Text(if (incidenciaToEdit == null) "Nueva incidencia" else "Editar incidencia")
        },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FormField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = "Titulo *",
                    error = if (showErrors) tituloError else null
                )
                Text("Categoria *", style = MaterialTheme.typography.labelMedium)
                Categoria.entries.forEach { opcion ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = opcion == categoria,
                                onClick = { categoria = opcion },
                                role = Role.RadioButton
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = opcion == categoria, onClick = null)
                        Text(opcion.etiqueta)
                    }
                }
                FormField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = "Descripcion *",
                    error = if (showErrors) descripcionError else null
                )
                FormField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = "Direccion *",
                    error = if (showErrors) direccionError else null
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (isFormValid) {
                        onConfirmar(titulo.trim(), categoria, descripcion.trim(), direccion.trim())
                    } else {
                        showErrors = true // ahora se ven los errores
                    }
                }
            ) {
                Text(if (incidenciaToEdit == null) "Guardar" else "Actualizar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}

