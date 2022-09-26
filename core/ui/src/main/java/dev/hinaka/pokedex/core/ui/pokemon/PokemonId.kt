package dev.hinaka.pokedex.core.ui.pokemon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import dev.hinaka.pokedex.core.ui.R
import dev.hinaka.pokedex.domain.Id

@Composable
fun PokemonId(
    id: Id,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.titleMedium,
) {
    val idText = id.toString().padStart(3, '0')
    Text(
        text = stringResource(id = R.string.pokemon_id_format, idText),
        modifier = modifier,
        style = style,
    )
}