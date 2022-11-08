package dev.hinaka.pokedex.feature.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.core.designsystem.component.PkdxAppBar
import dev.hinaka.pokedex.core.ui.type.PokemonType
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TypeScreen(
    uiState: TypeScreenUiState,
    appBarState: TopAppBarState,
    openDrawer: () -> Unit,
    onPrimaryTypeSelected: (Type?) -> Unit,
    onSecondaryTypeSelected: (Type?) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(appBarState)

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PkdxAppBar(
                title = "Type Dex",
                openDrawer = openDrawer,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        TypeScreenContent(
            uiState = uiState,
            onPrimaryTypeSelected = onPrimaryTypeSelected,
            onSecondaryTypeSelected = onSecondaryTypeSelected,
            modifier = Modifier.consumedWindowInsets(innerPadding),
            contentPadding = innerPadding
        )
    }
}

@Composable
private fun TypeScreenContent(
    uiState: TypeScreenUiState,
    onPrimaryTypeSelected: (Type?) -> Unit,
    onSecondaryTypeSelected: (Type?) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(contentPadding)
            .padding(horizontal = 16.dp),

        ) {
        PokemonTypesSelector(
            allTypes = uiState.allTypes,
            primaryType = uiState.selectedPrimaryType,
            secondaryType = uiState.selectedSecondaryType,
            onPrimaryTypeSelected = onPrimaryTypeSelected,
            onSecondaryTypeSelected = onSecondaryTypeSelected
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Damage Taken", modifier = Modifier.align(Alignment.CenterHorizontally))
        DamageTakenCard(
            damageRelation = uiState.damageRelation
        )
    }
}

@Composable
fun PokemonTypesSelector(
    allTypes: List<Type>,
    primaryType: Type?,
    secondaryType: Type?,
    onPrimaryTypeSelected: (Type?) -> Unit,
    onSecondaryTypeSelected: (Type?) -> Unit
) {
    var primaryTypeExpanded by remember { mutableStateOf(false) }
    var secondaryTypeExpanded by remember { mutableStateOf(false) }

    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Min)
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            typeSelector(
                label = primaryType?.name ?: "Select type",
                expanded = primaryTypeExpanded,
                description = "Primary type",
                selectableTypes = allTypes.filter { it != primaryType },
                onTypeButtonClicked = { primaryTypeExpanded = true },
                onTypeSelected = {
                    onPrimaryTypeSelected(it)
                    primaryTypeExpanded = false
                },
                onMenuDismissed = { primaryTypeExpanded = false }
            )

            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )

            typeSelector(
                label = secondaryType?.name ?: "Select type",
                expanded = secondaryTypeExpanded,
                description = "Secondary type",
                selectableTypes = allTypes.filter { it != primaryType && it != secondaryType },
                onTypeButtonClicked = { secondaryTypeExpanded = true },
                onTypeSelected = {
                    onSecondaryTypeSelected(it)
                    secondaryTypeExpanded = false
                },
                onMenuDismissed = { secondaryTypeExpanded = false }
            )
        }
    }
}

@Composable
fun RowScope.typeSelector(
    label: String,
    expanded: Boolean,
    description: String,
    selectableTypes: List<Type>,
    onTypeButtonClicked: () -> Unit,
    onTypeSelected: (Type?) -> Unit,
    onMenuDismissed: () -> Unit
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onTypeButtonClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelLarge
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onMenuDismissed,
            modifier = Modifier.fillMaxHeight(0.5f)
        ) {
            DropdownMenuItem(
                text = { Text(text = "Clear") },
                onClick = { onTypeSelected(null) }
            )
            selectableTypes.forEach {
                DropdownMenuItem(
                    text = { PokemonType(type = it) },
                    onClick = {
                        onTypeSelected(it)
                    }
                )
            }
        }
        Text(
            text = description,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun DamageTakenCard(
    damageRelation: DamageRelation
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            if (damageRelation.isEmpty) {
                Text(text = "Select a primary or/and secondary type to view damage relations.")
            } else {
                damageRelation(
                    label = "Weak against...",
                    typeDamageFactors = damageRelation.weakAgainstMap.toList()
                )
                damageRelation(
                    label = "Resistant against...",
                    typeDamageFactors = damageRelation.resistantAgainstMap.toList()
                )
                damageRelation(
                    label = "Normal damage from...",
                    typeDamageFactors = damageRelation.normalAgainstMap.toList()
                )
            }
        }
    }
}

@Composable
fun ColumnScope.damageRelation(
    label: String,
    typeDamageFactors: List<Pair<Type, DamageFactor>>
) {
    Text(
        text = label,
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(vertical = 8.dp)
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        typeDamageFactors.chunked(3).forEach { chunk ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                chunk.forEach { (type, damageFactor) ->
                    typeDamageFactor(type, damageFactor)
                }
            }
        }
    }
}

@Composable
fun RowScope.typeDamageFactor(
    type: Type,
    damageFactor: DamageFactor
) {
    PokemonType(type = type, modifier = Modifier.weight(1f))
}
