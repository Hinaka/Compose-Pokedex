package dev.hinaka.pokedex.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import dev.hinaka.pokedex.core.designsystem.icon.Icon
import dev.hinaka.pokedex.core.designsystem.icon.Icon.DrawableResourceIcon
import dev.hinaka.pokedex.core.designsystem.icon.Icon.ImageVectorIcon

@Composable
fun PokedexImage(
    icon: Icon,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {
    when (icon) {
        is DrawableResourceIcon -> Image(
            painter = painterResource(id = icon.id),
            contentDescription = contentDescription,
            modifier = modifier,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
        )
        is ImageVectorIcon -> Image(
            imageVector = icon.imageVector,
            contentDescription = contentDescription,
            modifier = modifier,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
        )
    }
}