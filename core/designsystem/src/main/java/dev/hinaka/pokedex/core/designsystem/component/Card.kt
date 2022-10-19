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
        contentColor = contentColor,
    )
}