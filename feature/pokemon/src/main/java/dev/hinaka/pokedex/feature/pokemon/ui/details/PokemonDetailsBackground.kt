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
package dev.hinaka.pokedex.feature.pokemon.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import dev.hinaka.pokedex.core.ui.type.getTypeContainerColors
import dev.hinaka.pokedex.domain.pokemon.Pokemon

@Composable
fun DetailsThemeProvider(
    pokemon: Pokemon,
    content: @Composable () -> Unit
) {
    val (containerColor, contentColor) = pokemon.types.getTypeContainerColors()

    val detailsTheme = DetailsTheme(
        primaryColor = containerColor,
        onPrimaryColor = contentColor
    )

    CompositionLocalProvider(LocalDetailsTheme provides detailsTheme) {
        content()
    }
}
