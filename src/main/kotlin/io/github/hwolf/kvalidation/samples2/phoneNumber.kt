package io.github.hwolf.kvalidation.samples2

import io.github.hwolf.kvalidation.ValidationBuilder
import io.github.hwolf.kvalidation.common.PhoneNumber
import io.github.hwolf.kvalidation.common.phoneNumber

fun <T> ValidationBuilder<T, String>.isGermanPhoneNumber() =  phoneNumber("DE",
    PhoneNumber.Option.OnlyForRegion,
    PhoneNumber.Option.Mobile,
    PhoneNumber.Option.FixedLine,
    PhoneNumber.Option.VOIP,
    PhoneNumber.Option.VoiceMail)
