package com.example.unitconverter.util

class OptionConverter {

    fun isBinaryToDecimal(from: String, to: String): Boolean {
        return isBinary(from) && isDecimal(to)
    }

    fun isBinaryToOctal(from: String, to: String): Boolean {
        return isBinary(from) && isOctal(to)
    }

    fun isBinaryToHex(from: String, to: String): Boolean {
        return isBinary(from) && isHexDecimal(to)
    }

    fun isDecimalToBinary(from: String, to: String): Boolean {
        return isDecimal(from) && isBinary(to)
    }

    fun isDecimalToOctal(from: String, to: String): Boolean {
        return isDecimal(from) && isOctal(to)
    }

    fun isDecimalToHex(from: String, to: String): Boolean {
        return isDecimal(from) && isHexDecimal(to)
    }


    fun isOctalToBinary(from: String, to: String): Boolean {
        return isOctal(from) && isBinary(to)
    }

    fun isOctalToDecimal(from: String, to: String): Boolean {
        return isOctal(from) && isDecimal(to)
    }

    fun isOctalToHex(from: String, to: String): Boolean {
        return isOctal(from) && isHexDecimal(to)
    }

    fun isHexToBinary(from: String, to: String): Boolean {
        return isHexDecimal(from) && isBinary(to)
    }

    fun isHexToDecimal(from: String, to: String): Boolean {
        return isHexDecimal(from) && isDecimal(to)
    }

    fun isHexToOctal(from: String, to: String): Boolean {
        return isHexDecimal(from) && isOctal(to)
    }

    private fun isBinary(input: String): Boolean {
        return input == TypeConvert.Binary.name
    }

    private fun isDecimal(input: String): Boolean {
        return input == TypeConvert.Decimal.name
    }

    private fun isOctal(input: String): Boolean {
        return input == TypeConvert.Octal.name
    }

    private fun isHexDecimal(input: String): Boolean {
        return input == TypeConvert.HexDecimal.name
    }

}