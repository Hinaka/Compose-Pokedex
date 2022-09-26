package dev.hinaka.pokedex.core.ui.pokemon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun PokemonName(
    name: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.titleMedium,
) {
    Text(
        text = name.replaceFirstChar { it.uppercase() },
        modifier = modifier,
        style = style
    )
}