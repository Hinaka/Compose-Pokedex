package dev.hinaka.pokedex.core.ui.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.hinaka.pokedex.domain.type.DamageFactor
import dev.hinaka.pokedex.domain.type.Type

@Composable
fun DamageRelationChart(
    damageRelationMap: Map<Type, DamageFactor>,
    modifier: Modifier = Modifier,
) {
    val (weakAgainst, rest) = remember(damageRelationMap) {
        damageRelationMap.toList().partition {
            it.second.isSuperEffective
        }
    }
    val (resistantAgainst, normalAgainst) = remember(rest) {
        rest.partition {
            it.second.isNotVeryEffective || it.second.isImmune
        }
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        DamageRelationSection(label = "Weak against...", damageRelations = weakAgainst)
        DamageRelationSection(label = "Resistant against...", damageRelations = resistantAgainst)
        DamageRelationSection(label = "Normal damage from...", damageRelations = normalAgainst)
    }
}

@Composable
private fun DamageRelationSection(
    label: String,
    damageRelations: List<Pair<Type, DamageFactor>>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = label,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        damageRelations.chunked(3).forEach { chunk ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                chunk.forEach { (type, damageFactor) ->
                    TypeDamageFactor(type, damageFactor, Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun TypeDamageFactor(
    type: Type,
    damageFactor: DamageFactor,
    modifier: Modifier = Modifier
) {
    PokemonType(type = type, modifier = modifier)
}