package com.obe.khanisorn_test_cal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalViewModel: ViewModel() {
    var number = mutableStateOf("0")
    var clear = mutableStateOf(true)
    var operand1 = mutableStateOf("")
    var operand2 = mutableStateOf("")
    var operation = mutableStateOf("")

    fun addDigit(digit: String){
        if (clear.value) {
            number.value = digit
            clear.value = false
        } else {
            number.value = if (number.value == "0") digit else number.value + digit
        }
    }

    fun addDot(dot: String){
        if (!number.value.contains(".")) {
            number.value += dot}
    }

    fun operate(operator: String) {
        if (operation.value.isNotEmpty() && !clear.value) {
            operand2.value = number.value
            val result = calculate(operand1.value, operand2.value, operation.value)
            number.value = result.toString()
            operand1.value = result.toString()
            operand2.value = ""
        } else {
            operand1.value = number.value
        }
        operation.value = operator
        clear.value = true
    }

    fun equal() {
        if (operation.value.isNotEmpty() && number.value.isNotEmpty()) {
            if (operand2.value.isEmpty()) {
                operand2.value = number.value
            }
            val result = calculate(operand1.value, operand2.value, operation.value)
            number.value = result.toString()
            operand1.value = result.toString()
            operand2.value = ""
            operation.value = ""
            clear.value = true
        }
    }

    private fun calculate(operand1: String, operand2: String, operation: String): Double
        = when (operation) {
            "+" -> operand1.toDouble() + operand2.toDouble()
            "-" -> operand1.toDouble() - operand2.toDouble()
            else -> Double.NaN
        }
}