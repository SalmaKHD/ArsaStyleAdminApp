package com.salmakhd.android.arsastyleadmin.common.inputvalidation

import com.salmakhd.android.arsastyleadmin.common.model.ValidationResult

fun validateBasicInput(value: String): ValidationResult {
    if (value.isEmpty()) {
        return ValidationResult(
            successful = false,
            errorMessage = "فیلد نباید خالی باشد"
        )
    }

    var inputValid = true
    // Check for non-printable characters
    value.forEach {
        if (it.code < 32) {
            inputValid = false
        }
    }

    // Check for potentially dangerous characters
    if (value.contains("<") || value.contains(">") || value.contains("$") ||
        value.contains("(") || value.contains(")") || value.contains("[") ||
        value.contains("]") || value.contains("{") || value.contains("}") ||
        value.contains("'") || value.contains("\"") || value.contains("\\") ||
        value.contains("/") || value.contains("|") || value.contains("*") ||
        value.contains("?") || value.contains("~") || value.contains("`") ||
        value.contains("!") || value.contains("\n") || value.contains("\r") ||
        value.contains("\t")
    ) {
        inputValid = false
    }

    return if(inputValid)
        ValidationResult(
            successful = true
        )
    else ValidationResult(
        successful = false,
        errorMessage = "فرمت اشتباه"
    )
}