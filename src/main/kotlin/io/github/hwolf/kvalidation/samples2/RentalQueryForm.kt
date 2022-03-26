package io.github.hwolf.kvalidation.samples2

import io.github.hwolf.kvalidation.common.email
import io.github.hwolf.kvalidation.equal
import io.github.hwolf.kvalidation.greater
import io.github.hwolf.kvalidation.maxLength
import io.github.hwolf.kvalidation.minLength
import io.github.hwolf.kvalidation.use
import io.github.hwolf.kvalidation.validator
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class RentalQuery(
    val period: RentalPeriod,
    val deliveryAddress: Address?,
    val contactData: ContactData,
    val message: String?,
    val termsAccepted: Boolean
) {
    data class RentalPeriod(
        val startDate: LocalDate,
        val startTime: LocalTime,
        val endDate: LocalDate,
        val endTime: LocalTime
    ) {
        val start: LocalDateTime = LocalDateTime.of(startDate, startTime)
        val end: LocalDateTime = LocalDateTime.of(endDate, endTime)
    }

    data class ContactData(
        val customerNumber: String?,
        val companyName: String?,
        val salutation: Salutation?,
        val firstName: String,
        val lastName: String,
        val address: Address?,
        val email: String,
        val phoneNumber: String?
    )

    enum class Salutation {
        @Suppress("unused")
        MR,
        @Suppress("unused")
        MRS,
        @Suppress("unused")
        MX
    }
}

@Suppress("unused")
val rentalQueryFormValidator = validator<RentalQuery> {
    RentalQuery::period {
        RentalQuery.RentalPeriod::startDate {
            greater(LocalDate.now())
        }
        RentalQuery.RentalPeriod::end {
            greater(RentalQuery.RentalPeriod::start)
        }
    }
    RentalQuery::deliveryAddress ifPresent {
        use(addressValidator)
    }
    RentalQuery::contactData  {
        RentalQuery.ContactData::customerNumber ifPresent {
            minLength(2)
            maxLength(20)
        }
        RentalQuery.ContactData::companyName ifPresent {
            minLength(2)
            maxLength(50)
        }
        //RentalQuery.ContactData:: salutation: RentalQuery.Salutation?,
        RentalQuery.ContactData::firstName ifPresent {
            minLength(2)
            maxLength(50)
        }
        RentalQuery.ContactData::lastName ifPresent {
            minLength(2)
            maxLength(50)
        }
        RentalQuery.ContactData::address ifPresent {
            use(addressValidator)
        }
        RentalQuery.ContactData::email {
            email()
        }
        RentalQuery.ContactData::phoneNumber ifPresent {
            isGermanPhoneNumber()
        }
    }
    RentalQuery::message ifPresent {
        minLength(2)
        maxLength(1000)
    }
    RentalQuery::termsAccepted {
        equal(true)
    }
}
