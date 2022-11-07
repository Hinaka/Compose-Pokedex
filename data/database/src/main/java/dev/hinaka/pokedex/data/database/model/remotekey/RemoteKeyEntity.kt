package dev.hinaka.pokedex.data.database.model.remotekey

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @ColumnInfo(name = "label") val label: String,
    @ColumnInfo(name = "nextOffset") val nextOffset: Int?,
)
