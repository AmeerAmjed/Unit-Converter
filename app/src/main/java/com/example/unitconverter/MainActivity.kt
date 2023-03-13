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
            val from = converterFrom.selectedItem.toString()
            val to = converterTo.selectedItem.toString()
            val result = when {
                option.isBinaryToDecimal(from, to) -> Convert.binaryToDecimal(input)
                option.isBinaryToOctal(from, to) -> Convert.binaryToOctal(input)
                option.isBinaryToHex(from, to) -> Convert.binaryToHex(input)
                option.isDecimalToBinary(from, to) -> Convert.decimalToBinary(input.toInt())
                option.isDecimalToOctal(from, to) -> Convert.decimalToOctal(input.toInt())
                option.isDecimalToHex(from, to) -> Convert.decimalToHex(input.toInt())
                option.isOctalToBinary(from, to) -> Convert.octalToBinary(input)
                option.isOctalToDecimal(from, to) -> Convert.octalToDecimal(input)
                option.isOctalToHex(from, to) -> Convert.octalToHex(input)
                option.isHexToBinary(from, to) -> Convert.hexToBinary(input)
                option.isHexToDecimal(from, to) -> Convert.hexToDecimal(input)
                option.isHexToOctal(from, to) -> Convert.hexToOctal(input)
                else -> null
            }

            if (result != null) {
                tryConvert(input) { result }
            } else {
                sameTypeConverter(from)
            }

        }
    }

    private fun <I, O> tryConvert(input: I, convert: (I) -> O) {
        try {
            binding.result.text = convert(input).toString()
            stateShowTextResult(true)
        } catch (error: Throwable) {
            showToast(getString(R.string.issue_convert))
        }
    }

    private fun sameTypeConverter(type: String) {
        showToast(getString(R.string.same_type_convert, type))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}