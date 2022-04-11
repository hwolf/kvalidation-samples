/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
