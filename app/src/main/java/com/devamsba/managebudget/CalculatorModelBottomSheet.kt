package com.devamsba.managebudget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.devamsba.managebudget.common.infra.OperationsHelper
import com.devamsba.managebudget.databinding.CalculatorModelBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CalculatorModelBottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = CalculatorModelBottomSheet()
    }

    lateinit var binding: CalculatorModelBottomSheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CalculatorModelBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialog()
        binding?.resultId.text = "0"

        initializeButtons()
    }

    private fun initDialog() {
        requireDialog().window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireDialog().window?.statusBarColor = requireContext().getColor(android.R.color.transparent)
    }
    var digitOnScreen = StringBuilder()
    var operation: Char = ' '
    var leftHandSide: Double = 0.0
    var rightHandSide: Double = 0.0



    private fun initializeButtons() {
        functionalButtons()
        operationalButtons()
        numericalButtons()
    }

    /**
     * This function initializes all of our numerical buttons from
     *  [0 - 9]
     */
    private fun numericalButtons() = with(binding){

        oneBtn.setOnClickListener {
            appendToDigitOnScreen("1")
        }

        twoBtn.setOnClickListener {
            appendToDigitOnScreen("2")
        }

        threeBtn.setOnClickListener {
            appendToDigitOnScreen("3")
        }

        fourBtn.setOnClickListener {
            appendToDigitOnScreen("4")
        }

        fiveBtn.setOnClickListener {
            appendToDigitOnScreen("5")
        }

        sixBtn.setOnClickListener {
            appendToDigitOnScreen("6")
        }

        sevenBtn.setOnClickListener {
            appendToDigitOnScreen("7")
        }

        eightBtn.setOnClickListener {
            appendToDigitOnScreen("8")
        }

        nineBtn.setOnClickListener {
            appendToDigitOnScreen("9")
        }

        zeroBtn.setOnClickListener {
            appendToDigitOnScreen("0")
        }

        dotBtn.setOnClickListener {
            appendToDigitOnScreen(".")
        }


    }

    /**
     *  Insert the button been clicked onto the screen so user can see
     *  inputs for the button clicked
     */
    private fun appendToDigitOnScreen(digit: String) = with(binding) {

        // Add each digit to our string builder
        digitOnScreen.append(digit)

        // display it on the screen of our mobile app
        resultId.text = digitOnScreen.toString()
    }

    /**
     *  Initialize the operation keys in our calculator like the
     *  addition key, subtraction key and the likes
     */
    private fun operationalButtons() = with(binding) {

        additionBtn.setOnClickListener {
            selectOperation('A')
        }

        subtractbBtn.setOnClickListener {
            selectOperation('B')
        }

        divideBtn.setOnClickListener {
            selectOperation('D')
        }

        multipyBtn.setOnClickListener {
            selectOperation('M')
        }

    }

    /**
     * Function to assign operational sign to our math calculations
     */
    private fun selectOperation(c: Char) {

        operation = c
        leftHandSide = digitOnScreen.toString().toDouble()
        digitOnScreen.clear()
        binding.resultId.text = "0"
    }

    /**
     * Handles functional operations in out application like
     * clear button, backspace button and the clear everything button
     */
    private fun functionalButtons() = with(binding) {

        clearEverythingBtn.setOnClickListener {
            digitOnScreen.clear()
        }

        clearBtn.setOnClickListener {
            clearDigit()
        }

        backspaceBtn.setOnClickListener {
            clearDigit()
        }

        eightBtn.setOnClickListener {
            performMathOperation()
        }

    }


    /**
     *  This function performs our Math Operation which is then showed on the screen.
     */
    private fun performMathOperation()  {

        rightHandSide = digitOnScreen.toString().toDouble()
        digitOnScreen.clear()

        when (operation) {

            'A' -> {
                val sum = OperationsHelper.add(leftHandSide, rightHandSide)
                binding.resultId.text = sum.toString()
                digitOnScreen.append(sum)
            }
            'S' -> {
                val subtract = OperationsHelper.subtract(leftHandSide, rightHandSide)
                binding.resultId.text = subtract.toString()
                digitOnScreen.append(subtract)
            }
            'M' -> {
                val multiply = OperationsHelper.multiply(leftHandSide, rightHandSide)
                binding.resultId.text = multiply.toString()
                digitOnScreen.append(multiply)
            }
            'D' -> {
                val divide = OperationsHelper.divide(leftHandSide, rightHandSide)
                binding.resultId.text = divide.toString()
                digitOnScreen.append(divide)
            }

        }

    }

    /**
     *  This function remove the last digit on the screen.
     */
    private fun clearDigit() {

        val length = digitOnScreen.length

        digitOnScreen.deleteCharAt(length - 1)
        binding.resultId.text = digitOnScreen.toString()

    }


}
