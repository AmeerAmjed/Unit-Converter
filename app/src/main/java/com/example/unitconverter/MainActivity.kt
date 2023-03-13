package com.example.unitconverter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unitconverter.databinding.ActivityMainBinding
import com.example.unitconverter.util.OptionConverter


class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val option: OptionConverter = OptionConverter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val converterFrom: Spinner = binding.selectConverterFrom
        val converterTo: Spinner = binding.selectConverterTo

        initSpinner(converterFrom, converterTo)

        binding.btnConverter.setOnClickListener {
            val input = binding.inputConverter.text.toString()

            if (option.isBinaryToDecimal(converterFrom, converterTo)) {
                tryConvert(input) { Convert.binaryToDecimal(input) }
            } else if (option.isBinaryToOctal(converterFrom, converterTo)) {
                tryConvert(input) { Convert.binaryToOctal(input) }
            } else if (option.isBinaryToHex(converterFrom, converterTo)) {
                tryConvert(input) { Convert.binaryToHex(input) }
            } else if (option.isDecimalToBinary(converterFrom, converterTo)) {
                tryConvert(input) { Convert.decimalToBinary(input.toInt()) }
            }  else if (option.isDecimalToOctal(converterFrom, converterTo)) {
                tryConvert(input) { Convert.decimalToOctal(input.toInt()) }
            }  else if (option.isDecimalToHex(converterFrom, converterTo)) {
                tryConvert(input) { Convert.decimalToHex(input.toInt()) }
            }



            else if (option.isBinaryToBinary(converterFrom, converterTo)) {
                sameTypeConverter(converterFrom.selectedItem.toString())
            }else if (option.isDecimalToDecimal(converterFrom, converterTo)) {
                sameTypeConverter(converterFrom.selectedItem.toString())
            }


        }

    }

    private fun initSpinner(converterFrom: Spinner, converterTo: Spinner) {
        val optionConverter = listOf("Binary", "Decimal", "Octal", "HexDecimal")

        val adapterConverterFrom = ArrayAdapter(this, R.layout.item_spinner, optionConverter)
        adapterConverterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        converterFrom.adapter = adapterConverterFrom

        val adapterConverterTo = ArrayAdapter(this, R.layout.item_spinner, optionConverter)
        adapterConverterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        converterTo.adapter = adapterConverterTo
        converterTo.setSelection(1)
    }



    private fun <I, O> tryConvert(input: I, convert: (I) -> O) {
        try {
            binding.result.text = convert(input).toString()
        } catch (error: Throwable) {
            showToast("problem")
        }
    }

    private fun sameTypeConverter(type: String) {
        showToast(getString(R.string.same_type_convert, type))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}