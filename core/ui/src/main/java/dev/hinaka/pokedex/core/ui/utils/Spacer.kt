package dev.hinaka.pokedex.core.ui.utils

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun RowScope.spacer(dp: Dp) = Spacer(modifier = Modifier.width(dp))

@Composable
fun ColumnScope.spacer(dp: Dp) = Spacer(modifier = Modifier.height(dp))