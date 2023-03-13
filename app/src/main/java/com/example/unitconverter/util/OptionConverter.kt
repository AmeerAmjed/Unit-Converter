package com.example.unitconverter.util

import android.widget.Spinner

class OptionConverter {

    fun isBinaryToDecimal(from: Spinner, to: Spinner): Boolean {
        return isBinary(from) && isDecimal(to)
    }

    fun isBinaryToOctal(from: Spinner, to: Spinner): Boolean {
        return isBinary(from) && isOctal(to)
    }

    fun isBinaryToHex(from: Spinner, to: Spinner): Boolean {
        return isBinary(from) && isHexDecimal(to)
    }

    fun isBinaryToBinary(from: Spinner, to: Spinner): Boolean {
        return isBinary(from) && isBinary(to)
    }


    fun isDecimalToBinary(from: Spinner, to: Spinner): Boolean {
        return isDecimal(from) && isBinary(to)
    }
    fun isDecimalToDecimal(from: Spinner, to: Spinner): Boolean {
        return isDecimal(from) && isDecimal(to)
    }

    fun isDecimalToOctal(from: Spinner, to: Spinner): Boolean {
        return isDecimal(from) && isOctal(to)
    }
    fun isDecimalToHex(from: Spinner, to: Spinner): Boolean {
        return isDecimal(from) && isHexDecimal(to)
    }


    fun isOctalToBinary(from: Spinner, to: Spinner): Boolean {
        return isOctal(from) && isBinary(to)
    }
    fun isOctalToDecimal(from: Spinner, to: Spinner): Boolean {
        return isOctal(from) && isDecimal(to)
    }
    fun isOctalToOctal(from: Spinner, to: Spinner): Boolean {
        return isOctal(from) && isOctal(to)
    }
    fun isOctalToHex(from: Spinner, to: Spinner): Boolean {
        return isOctal(from) && isHexDecimal(to)
    }
    private fun isBinary(input: Spinner): Boolean {
        return input.selectedItem == TypeConvert.Binary.name
    }

    private fun isDecimal(input: Spinner): Boolean {
        return input.selectedItem == TypeConvert.Decimal.name
    }

    private fun isOctal(input: Spinner): Boolean {
        return input.selectedItem == TypeConvert.Octal.name
    }

    private fun isHexDecimal(input: Spinner): Boolean {
        return input.selectedItem == TypeConvert.HexDecimal.name
    }

}