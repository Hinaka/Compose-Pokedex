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
package dev.hinaka.pokedex.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PkdxCard(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PkdxCardDefaults.cardContentPadding(),
    colors: CardColors = PkdxCardDefaults.cardColors(),
    content: @Composable ColumnScope.() -> Unit
) {
    ElevatedCard(modifier = modifier, colors = colors) {
        Column(modifier = Modifier.padding(contentPadding), content = content)
    }
}

object PkdxCardDefaults {
    val HorizontalPadding = 16.dp
    val VerticalPadding = 16.dp

    fun cardContentPadding(): PaddingValues {
        return PaddingValues(
            horizontal = HorizontalPadding,
            vertical = VerticalPadding
        )
    }

    @Composable
    fun cardColors(
        containerColor: Color = MaterialTheme.colorScheme.surface,
        contentColor: Color = contentColorFor(backgroundColor = containerColor)
    ) = CardDefaults.elevatedCardColors(
        containerColor = containerColor,
        contentColor = contentColor
    )
}
