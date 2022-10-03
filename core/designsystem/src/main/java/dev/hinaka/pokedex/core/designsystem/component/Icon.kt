package dev.hinaka.pokedex.core.designsystem.component

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import dev.hinaka.pokedex.core.designsystem.icon.Icon
import dev.hinaka.pokedex.core.designsystem.icon.Icon.DrawableResourceIcon
import dev.hinaka.pokedex.core.designsystem.icon.Icon.ImageVectorIcon

@Composable
fun PokedexIcon(
    icon: Icon,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    when (icon) {
        is DrawableResourceIcon -> Icon(
            painter = painterResource(id = icon.id),
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint,
        )
        is ImageVectorIcon -> Icon(
            imageVector = icon.imageVector,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint,
        )
    }
}