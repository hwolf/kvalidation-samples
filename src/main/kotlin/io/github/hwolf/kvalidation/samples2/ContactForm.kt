package io.github.hwolf.kvalidation.samples2

import io.github.hwolf.kvalidation.common.email
import io.github.hwolf.kvalidation.equal
import io.github.hwolf.kvalidation.hasValue
import io.github.hwolf.kvalidation.maxLength
import io.github.hwolf.kvalidation.minLength
import io.github.hwolf.kvalidation.use
import io.github.hwolf.kvalidation.validator

data class ContactForm(
    val area: String,
    val message: String,
    val firstName: String,
    val lastName: String,
    val address: Address?,
    val country: String?,
    val email: String,
    val phoneNumber: String?,
    val termsAccepted: Boolean
)

@Suppress("unused")
val contactFormValidator = validator<ContactForm> {
    ContactForm::area {
        hasValue("group", "investorrelations", "press")
    }
    ContactForm::message  {
        minLength(2)
        maxLength(1000)
    }
    ContactForm::firstName ifPresent {
        minLength(2)
        maxLength(50)
    }
    ContactForm::lastName ifPresent {
        minLength(2)
        maxLength(50)
    }
    ContactForm::address ifPresent {
        use(addressValidator)
    }
    ContactForm::country ifPresent {
        minLength(2)
        maxLength(50)
    }
    ContactForm::email {
        email()
    }
    ContactForm::phoneNumber ifPresent {
        isGermanPhoneNumber()
    }
    ContactForm::termsAccepted {
        equal(true)
    }
}
