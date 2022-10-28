/*
 * Copyright 2022 Hinaka (Trung Nguyễn Minh Trần)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.hinaka.pokedex.domain.pokemon

import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.common.DomainBuilder
import dev.hinaka.pokedex.domain.common.build

class EggGroupBuilder(private val id: Int) : DomainBuilder<EggGroup> {

    var name: String? = null

    override fun build(): EggGroup {
        return EggGroup(
            id = Id(id),
            name = name.orEmpty()
        )
    }
}

fun eggGroup(id: Int, init: EggGroupBuilder.() -> Unit): EggGroup = build(EggGroupBuilder(id), init)
