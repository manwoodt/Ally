package com.study.ally.presentation.screens.passport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ContactsBlock(
    doctor: String,
    contact: String,
    isEditing: Boolean,
    onDoctorChange: (String) -> Unit,
    onContactChange: (String) -> Unit
) {
    Column {

        Text("📞 Контакты")

        Spacer(Modifier.height(4.dp))

        OutlinedTextField(
            value = doctor,
            onValueChange = onDoctorChange,
            readOnly = !isEditing,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text("Лечащий врач") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = contact,
            onValueChange = onContactChange,
            readOnly = !isEditing,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text("Родственник") }
        )
    }
}