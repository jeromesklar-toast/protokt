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

package com.toasttab.protokt.gradle

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.the

const val CODEGEN_NAME = "protoc-gen-protokt"

const val EXTENSIONS = "protoktExtensions"

const val TEST_EXTENSIONS = "testProtoktExtensions"

fun configureProtokt(project: Project, resolveBinary: () -> String) {
    createExtensionConfigurations(project)

    val ext = project.extensions.create<ProtoktExtension>("protokt")

    configureProtobufPlugin(project, ext, resolveBinary())
}

private fun createExtensionConfigurations(project: Project) {
    val extensionsConfiguration = project.configurations.create(EXTENSIONS)

    project.configurations.named("api") {
        extendsFrom(extensionsConfiguration)
    }

    val testExtensionsConfiguration = project.configurations.create(TEST_EXTENSIONS)

    project.configurations.named("testApi") {
        extendsFrom(testExtensionsConfiguration)
    }
}

fun Project.resolveProtoktCoreDep() =
    if (the<ProtoktExtension>().lite) {
        "protokt-core-lite"
    } else {
        "protokt-core"
    }
