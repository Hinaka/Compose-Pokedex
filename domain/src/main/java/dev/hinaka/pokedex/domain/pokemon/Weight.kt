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

@JvmInline
value class Weight private constructor(
    val g: Int
) {
    val hg get() = g / 100f
    val kg get() = g / 1000f

    companion object {
        fun gram(g: Int) = Weight(g)
        fun hectogram(hg: Int) = Weight(hg * 100)
        fun kilogram(kg: Int) = Weight(kg * 1000)
    }
}

val EmptyWeight = Weight.gram(-1)
