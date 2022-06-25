package com.devamsba.managebudget.common.domain.validator

import com.devamsba.managebudget.R
import com.devamsba.managebudget.common.infra.validator.BaseValidator
import com.devamsba.managebudget.common.infra.validator.ValidateResult

class EmptyValidator (val input: String): BaseValidator(){
    override fun validate(): ValidateResult {
        val isValid = input.isNotEmpty()
        return ValidateResult(
            isValid,
            if (isValid) R.string.text_validation_success else R.string.text_validation_error
        )
    }

}