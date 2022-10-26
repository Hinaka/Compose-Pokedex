package dev.hinaka.pokedex.domain.pokemon

data class ImageUrls(
    val officialArtwork: String,
    val frontDefault: String,
    val frontShiny: String,
    val backDefault: String,
    val backShiny: String
)

val EmptyImageUrls = ImageUrls(
    officialArtwork = "",
    frontDefault = "",
    frontShiny = "",
    backDefault = "",
    backShiny = ""
)


