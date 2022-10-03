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
value class Height private constructor(
    val centimeter: Int
) {
    val decimeter get() = centimeter / 10f
    val meter get() = centimeter / 100f

    companion object {
        fun centimeter(cm: Int) = Height(cm)
        fun decimeter(dm: Int) = Height(dm * 10)
        fun meter(m: Int) = Height(m * 100)
    }
}

val EmptyHeight = Height.centimeter(-1)
