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