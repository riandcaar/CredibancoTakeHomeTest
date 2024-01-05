package com.credibanco.transacciones.ui.models

data class Transaction(
    val id: String,
    val commerceCode: String = "000123",
    val terminalCode: String = "000ABC",
    val amount: String,
    val card: String = "1234567890123456"
)