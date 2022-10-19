package dev.hinaka.pokedex.core.designsystem.component

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.icon.Icon

@Composable
fun PkdxOutlinedButton(
    onClick: () -> Unit,
    icon: Icon,
    label: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    OutlinedButton(
        onClick = onClick, modifier = modifier,
        border = ButtonDefaults.outlinedButtonBorder.copy(
            brush = SolidColor(color)
        )
    ) {
        PokedexImage(icon = icon, contentDescription = "", colorFilter = ColorFilter.tint(color))
        Space(dp = 8.dp)
        Text(
            text = label.uppercase(),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            color = color
        )
    }
}