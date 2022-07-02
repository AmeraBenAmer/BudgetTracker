package com.devamsba.managebudget.common.infra

class OperationsHelper {

    companion object {

        /**
         * Performs addition function
         */
        fun add(left_hand_side: Double, right_hand_side: Double): Double {
            val sum = left_hand_side + right_hand_side
            return sum
        }

        /**
         * Performs subtract function
         */
        fun subtract(left_hand_side: Double, right_hand_side: Double): Double {
            val subtract = left_hand_side - right_hand_side
            return subtract
        }

        /**
         * Performs division function
         */
        fun divide(left_hand_side: Double, right_hand_side: Double): Double {
            val divide = left_hand_side / right_hand_side
            return divide
        }

        /**
         * Performs multiplication function
         */
        fun multiply(left_hand_side: Double, right_hand_side: Double): Double {
            val product = left_hand_side * right_hand_side
            return product
        }
    }

}