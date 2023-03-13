package com.example.unitconverter

object Convert {
    fun binaryToDecimal(binary: String): Long {
        return binary.toLong(2)
    }
    fun binaryToOctal(binary: String): Long {
        return java.lang.Long.parseLong(
            Integer.toOctalString(
                Integer.parseInt(
                    binary,
                    2,
                )
            )
        )
    }

    fun binaryToHex(binary: String): String {
        return Integer.parseInt(binary, 2).toString(16).uppercase()
    }


    fun decimalToBinary(decimal: Int): String {
        return decimal.toString(2)
    }

    fun decimalToOctal(decimal: Int): String {
        return decimal.toString(8)
    }

    fun decimalToHex(decimal: Int): String {
        return decimal.toString(16).uppercase()
    }

    fun octalToDecimal(octal: String): Int {
        return Integer.parseInt(octal, 8)
    }

    fun octalToBinary(octal: String): String {
        return octalToDecimal(octal).toString(2).uppercase()
    }

    fun octalToHex(octal: String): String {
        return octalToDecimal(octal).toString(16).uppercase()
    }

    fun hexToDecimal(octal: String): Int {
        return Integer.parseInt(octal, 16)
    }

    fun hexToBinary(octal: String): String {
        return hexToDecimal(octal).toString(2).uppercase()
    }

    fun hexToOctal(octal: String): String {
        return hexToDecimal(octal).toString(8).uppercase()
    }
}