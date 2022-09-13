package dev.hinaka.pokedex.data.repository.mapper

import dev.hinaka.pokedex.data.database.model.ItemEntity
import dev.hinaka.pokedex.data.network.model.NetworkItem
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.Item

fun ItemEntity.toDomain() = Item(
    id = Id(id),
    name = name.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    effect = shortEffect.orEmpty(),
)

fun NetworkItem.toEntity() = ItemEntity(
    id = id ?: -1,
    name = name,
    imageUrl = imageUrl,
    shortEffect = effect,
)

fun List<ItemEntity>.toDomain() = map { it.toDomain() }

fun List<NetworkItem>.toEntity() = map { it.toEntity() }
