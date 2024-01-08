package com.credibanco.transacciones.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.credibanco.transacciones.data.local.entities.TransactionEntity

@Composable
fun CardTransactionInfoComplete (transaction: TransactionEntity){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Transaction ID: ${transaction.id}", fontWeight = FontWeight.Bold)
            Text(text = "Commerce Code: ${transaction.commerceCode}")
            Text(text = "Terminal Code: ${transaction.terminalCode}")
            Text(text = "Amount: ${transaction.amount}")
            Text(text = "Card: ${transaction.card}")
            Text(text = "Receipt: ${transaction.receipt}", fontWeight = FontWeight.Bold)
            Text(text = "Annulment Code: ${transaction.annulmentCode}", fontWeight = FontWeight.Bold)
            Text(text = "Status: ${transaction.status}")

        }
    }
}