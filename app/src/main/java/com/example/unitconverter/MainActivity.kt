package com.example.unitconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.unitconverter.util.OptionConverter


class MainActivity() : AppCompatActivity() {

    private lateinit var option: OptionConverter

    private lateinit var converterFrom: Spinner
    private lateinit var converterTo: Spinner
    private lateinit var inputConverter: EditText
    private lateinit var btnCleanInput: ImageButton
    private lateinit var btnConverter: AppCompatButton
    private lateinit var textResult: TextView
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initView()
        buttonConverterListener()
        textListener()
        cleanInput()
    }

    private fun initView() {
        option = OptionConverter()
        converterFrom = findViewById(R.id.select_converter_from)
        converterTo = findViewById(R.id.select_converter_to)
        inputConverter = findViewById(R.id.input_converter)
        btnCleanInput = findViewById(R.id.btn_clean_input)
        btnConverter = findViewById(R.id.btn_converter)
        textResult = findViewById(R.id.text_title_result)
        result = findViewById(R.id.result)
        initSpinner()
    }

    private fun initSpinner() {
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
        inputConverter.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(input: CharSequence?, start: Int, before: Int, count: Int) {
                btnCleanInput.run {
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
        btnCleanInput.setOnClickListener {
            inputConverter.text.clear()
            stateShowTextResult(false)
        }
    }

    private fun stateShowTextResult(state: Boolean) {
        val stateShow = if (state) View.VISIBLE else View.INVISIBLE
        textResult.visibility = stateShow
        result.visibility = stateShow
    }

    private fun buttonConverterListener() {
        btnConverter.setOnClickListener {
            val input = inputConverter.text.toString()
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
            result.text = convert(input).toString()
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