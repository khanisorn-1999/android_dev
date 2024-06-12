package com.obe.khanisorn_test_cal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalViewModel: ViewModel() {
    var number = mutableStateOf("0")
    var clear = mutableStateOf(true)
    var operand1 = mutableStateOf("")
    var operand2 = mutableStateOf("")
    var operand3 = mutableStateOf("")
    var operation = mutableStateOf("")
    var adjacentEqual = mutableStateOf(true)

    fun addDigit(digit: String){
        if (clear.value) {
            number.value = digit
            clear.value = false
        } else {
            number.value = if (number.value == "0") digit else number.value + digit
        }
    }

    fun addDot(dot: String){
        if (clear.value){
            number.value = "0"
        }
        if (!number.value.contains(".")) {
            number.value += dot
            clear.value = false
        }
    }

    fun operate(operator: String) {
        if (operation.value.isNotEmpty() && !clear.value) {
            operand2.value = number.value
            val result = calculate(operand1.value, operand2.value, operation.value)
            number.value = trim(result)
            operand1.value = result.toString()
            operand2.value = ""
        } else {
            operand1.value = number.value
        }
        operation.value = operator
        adjacentEqual.value = false
        clear.value = true
    }

    fun equal() {
        if (number.value.isNotEmpty() && operation.value.isEmpty()) {
            return
        }
        if (adjacentEqual.value){
            val result = calculate(operand1.value, operand3.value, operation.value)
            number.value = trim(result)
            operand1.value = result.toString()
            clear.value = true}
        else{
            if (operation.value.isNotEmpty() && operand2.value.isEmpty()) {
                operand2.value = number.value }
            if (operation.value.isNotEmpty()) {
                val result = calculate(operand1.value, operand2.value, operation.value)
                number.value = trim(result)
                operand3.value = operand2.value
                operand1.value = result.toString()
                operand2.value = ""
                adjacentEqual.value = true
                clear.value = true }
        }

    }

    private fun trim(number: Double): String{
        if (number % 1 == 0.0) {
            return number.toInt().toString()
        } else {
            return number.toString()
        }
    }
    private fun calculate(operand1: String, operand2: String, operation: String): Double
            = when (operation) {
        "+" -> operand1.toDouble() + operand2.toDouble()
        "-" -> operand1.toDouble() - operand2.toDouble()
        else -> 0.0
    }

}