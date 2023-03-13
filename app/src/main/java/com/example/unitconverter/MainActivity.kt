package com.example.unitconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
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
        buttonConverterListener(converterFrom, converterTo)
        textListener()
        cleanInput()
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


    private fun textListener() {
        binding.inputConverter.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(input: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnCleanInput.run {
                    visibility = if (input.toString().isEmpty()) {
                        View.INVISIBLE
                    } else {
                        View.VISIBLE
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        })
    }


    private fun cleanInput() {
        binding.btnCleanInput.setOnClickListener {
            binding.inputConverter.text.clear()
            stateShowTextResult(false)
        }
    }

    private fun stateShowTextResult(state: Boolean) {
        val stateShow = if (state) View.VISIBLE else View.INVISIBLE
        binding.run {
            textTitleResult.visibility = stateShow
            result.visibility = stateShow
        }
    }

    private fun buttonConverterListener(converterFrom: Spinner, converterTo: Spinner) {
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
            } else if (option.isDecimalToOctal(converterFrom, converterTo)) {
                tryConvert(input) { Convert.decimalToOctal(input.toInt()) }
            } else if (option.isDecimalToHex(converterFrom, converterTo)) {
                tryConvert(input) { Convert.decimalToHex(input.toInt()) }
            } else if (option.isOctalToBinary(converterFrom, converterTo)) {
                tryConvert(input) { Convert.octalToBinary(input) }
            } else if (option.isOctalToDecimal(converterFrom, converterTo)) {
                tryConvert(input) { Convert.octalToDecimal(input) }
            } else if (option.isOctalToHex(converterFrom, converterTo)) {
                tryConvert(input) { Convert.octalToHex(input) }
            } else if (option.isHexToBinary(converterFrom, converterTo)) {
                tryConvert(input) { Convert.hexToBinary(input) }
            } else if (option.isHexToDecimal(converterFrom, converterTo)) {
                tryConvert(input) { Convert.hexToDecimal(input) }
            } else if (option.isHexToOctal(converterFrom, converterTo)) {
                tryConvert(input) { Convert.hexToOctal(input) }
            } else if (
                option.isBinaryToBinary(converterFrom, converterTo)
                || option.isOctalToOctal(converterFrom, converterTo)
                || option.isDecimalToDecimal(converterFrom, converterTo)
                || option.isHexToHex(converterFrom, converterTo)
            ) {
                sameTypeConverter(converterFrom.selectedItem.toString())
            }
        }
    }

    private fun <I, O> tryConvert(input: I, convert: (I) -> O) {
        try {
            binding.result.text = convert(input).toString()
            stateShowTextResult(true)
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