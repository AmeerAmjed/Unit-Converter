package com.example.unitconverter.util

import android.widget.Spinner

class OptionConverter {

    fun isBinary(input: Spinner): Boolean {
        return input.selectedItem == TypeConvert.Binary.name
    }

    fun isDecimal(input: Spinner): Boolean {
        return input.selectedItem == TypeConvert.Decimal.name
    }

    fun isOctal(input: Spinner): Boolean {
        return input.selectedItem == TypeConvert.Octal.name
    }

    fun isHexDecimal(input: Spinner): Boolean {
        return input.selectedItem == TypeConvert.HexDecimal.name
    }

}