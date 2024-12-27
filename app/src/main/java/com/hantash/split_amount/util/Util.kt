package com.hantash.split_amount.util

fun calculateTotalTip(totalBill: String, tipPercentage: Int): Double {
    val bill = if (totalBill.isNotEmpty()) totalBill.toDouble() else 0.0
    return if (bill > 1) (bill * tipPercentage) / 100 else 0.0
}

fun calculateBillPerPerson(totalBill: String, tipPercentage: Int, splitBy: Int): Double {
    val bill = if (totalBill.isNotEmpty()) totalBill.toDouble() else 0.0
    val billTotal = bill + calculateTotalTip(totalBill, tipPercentage)
    return billTotal / splitBy
}
