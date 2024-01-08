package com.credibanco.transacciones.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.credibanco.transacciones.data.local.entities.TransactionEntity

@Composable
fun CardTransactionInfo(
    transaction: TransactionEntity,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Receipt: ${transaction.receipt}")
            Text(text = "Annulment Code: ${transaction.annulmentCode}")
            Text(text = "Status: ${transaction.status}")
        }
    }
}