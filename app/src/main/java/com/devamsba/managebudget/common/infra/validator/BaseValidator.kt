package com.devamsba.managebudget.common.infra.validator

import com.devamsba.managebudget.R

abstract class BaseValidator : IValidator {
    companion object {
        fun validate(vararg validators: IValidator): ValidateResult {
            validators.forEach {
                val result = it.validate()
                if (!result.isSuccess) {
                    return result
                }
            }
            return ValidateResult(true, R.string.text_validation_success)
        }
    }
}