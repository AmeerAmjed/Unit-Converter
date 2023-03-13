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


}