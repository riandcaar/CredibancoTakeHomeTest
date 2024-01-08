package com.credibanco.transacciones.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.credibanco.transacciones.R
import com.credibanco.transacciones.data.local.entities.TransactionEntity

@Composable
fun CardTransactionInfoComplete(transaction: TransactionEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                stringResource(id = R.string.transaction_id),
                fontWeight = FontWeight.Bold
            )
            Text(transaction.id)
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                (stringResource(id = R.string.commerce_code)),
                fontWeight = FontWeight.Bold
            )
            Text(transaction.commerceCode)
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                (stringResource(id = R.string.commerce_code)),
                fontWeight = FontWeight.Bold
            )

            Text(transaction.terminalCode)
            Spacer(modifier = Modifier.height(2.dp))

            Text((stringResource(id = R.string.amount)),
                fontWeight = FontWeight.Bold)

            Text(transaction.amount)
            Spacer(modifier = Modifier.height(2.dp))


            Text((stringResource(id = R.string.card)),
                fontWeight = FontWeight.Bold)
            Text(transaction.card)
            Spacer(modifier = Modifier.height(2.dp))


            Text((stringResource(id = R.string.receipt)),
                fontWeight = FontWeight.Bold)

            Text(transaction.receipt)
                    Spacer(modifier = Modifier.height(2.dp))

            Text(
                (stringResource(id = R.string.annulment_code)),
                fontWeight = FontWeight.Bold)
            Text(transaction.annulmentCode)
                    Spacer(modifier = Modifier.height(2.dp))

            Text((stringResource(id = R.string.receipt)),
                fontWeight = FontWeight.Bold)

            Text(transaction.status)

        }
    }
}