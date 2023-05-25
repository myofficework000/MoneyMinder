package com.business.money_minder.domain.model

data class Account(
    val account: String, val amount: Double,
    val income: Double, val expense: Double
)