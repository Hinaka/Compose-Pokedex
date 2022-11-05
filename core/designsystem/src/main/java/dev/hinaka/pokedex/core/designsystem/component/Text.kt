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

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        color = color,
        modifier = modifier
            .border(
                width = Dp.Hairline,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 8.dp, horizontal = 8.dp)
    )
}

@Composable
fun OutlinedText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        color = color,
        modifier = modifier
            .border(
                width = Dp.Hairline,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 8.dp, horizontal = 8.dp)
    )
}

@Composable
fun SubLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = LocalContentColor.current.copy(alpha = 0.5f),
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier
    )
}
