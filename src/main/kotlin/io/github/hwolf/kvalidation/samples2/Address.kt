package io.github.hwolf.kvalidation.samples2

import io.github.hwolf.kvalidation.common.germanZipCode
import io.github.hwolf.kvalidation.maxLength
import io.github.hwolf.kvalidation.minLength
import io.github.hwolf.kvalidation.validator

data class Address(
    val street: String,
    val zipCode: String,
    val town: String
)

val addressValidator = validator<Address> {
    Address::street {
        minLength(2)
        maxLength(50)
    }
    Address::zipCode {
        germanZipCode()
    }
    Address::town {
        minLength(2)
        maxLength(50)
    }
}
