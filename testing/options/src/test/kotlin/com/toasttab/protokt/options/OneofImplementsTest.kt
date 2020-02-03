/*
 * Copyright (c) 2019 Toast Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.toasttab.protokt.options

import com.google.common.truth.Truth.assertThat
import com.toasttab.model.ContainsOneofThatImplements
import com.toasttab.model.Id
import com.toasttab.model.ImplementsOneof1
import com.toasttab.model.OneofModel
import org.junit.Test

class OneofImplementsTest {
    private val obj =
        ContainsOneofThatImplements {
            implementingOneof = ContainsOneofThatImplements.ImplementingOneof.ImplementsOne(
                ImplementsOneof1 { id = Id("val") }
            )
        }

    @Test
    fun `property shared between oneof types can be assigned and accessed without switching`() {
        val assigned: OneofModel = obj.implementingOneof

        assertThat(assigned.id).isEqualTo(Id("val"))
    }
}
