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
package io.github.hwolf.kvalidation.samples

import io.github.hwolf.kvalidation.common.email
import io.github.hwolf.kvalidation.equal
import io.github.hwolf.kvalidation.greaterOrEqual
import io.github.hwolf.kvalidation.maxLength
import io.github.hwolf.kvalidation.minLength
import io.github.hwolf.kvalidation.notBlank
import io.github.hwolf.kvalidation.validator

data class UserProfile(
    val name: String,
    val age: Int?,
    val password: String?,
    val confirmPassword: String?,
    val email: String?
)

val userProfileValidator = validator {
    UserProfile::name {
        notBlank()
        hasMinLength(6)
    }
    UserProfile::age required {
        greaterOrEqual(18)
    }
    UserProfile::password required {
        minLength(8)
        maxLength(40)
    }
    UserProfile::confirmPassword required {
        equal(UserProfile::password)
    }
    UserProfile::email ifPresent {
        email()
    }
}
