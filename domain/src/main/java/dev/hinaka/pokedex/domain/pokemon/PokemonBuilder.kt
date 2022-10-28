package dev.hinaka.pokedex.domain.pokemon

import dev.hinaka.pokedex.domain.Ability
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.move.DamageClass
import dev.hinaka.pokedex.domain.move.LearnMethod
import dev.hinaka.pokedex.domain.move.LearnableMove
import dev.hinaka.pokedex.domain.move.Move
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio
import dev.hinaka.pokedex.domain.pokemon.Pokemon.ImageUrls
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Species
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Training
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Training.GrowthRate
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.domain.type.Type.Identifier.GRASS
import dev.hinaka.pokedex.domain.type.TypeIdentifier
import dev.hinaka.pokedex.domain.type.TypeIdentifier.UNKNOWN

@DslMarker
annotation class PokemonMarker

@PokemonMarker
class TypeBuilder(private val id: Id) {

    var identifier: Type.Identifier? = null
    var name: String? = null

    fun build(): Type {
        return Type(
            id = id,
            identifier = identifier ?: GRASS,
            name = name.orEmpty()
        )
    }
}

@PokemonMarker
class AbilityBuilder(private val id: Id) {

    var name: String = ""
    var effect: String = ""

    fun build(): Ability {
        return Ability(
            id = id,
            name = name,
            effect = effect
        )
    }
}

@PokemonMarker
class SpeciesBuilder {

    var flavorText: String = ""
    var heightInDecimeter = 0
    var weightInHectogram = 0

    fun build(): Species {
        return Species(
            flavorText = flavorText,
            height = Height.decimeter(heightInDecimeter),
            weight = Weight.hectogram(weightInHectogram)
        )
    }
}

@PokemonMarker
class StatsBuilder {

    var hp = 0
    var attack = 0
    var defense = 0
    var spAttack = 0
    var spDefense = 0
    var speed = 0

    fun build(): Stats {
        return Stats(
            hp = hp,
            attack = attack,
            defense = defense,
            specialAttack = spAttack,
            specialDefense = spDefense,
            speed = speed,
        )
    }
}

@PokemonMarker
class LearnableMoveBuilder(private val id: Id) {

    var name: String = ""
    var typeIdentifier: TypeIdentifier = UNKNOWN
    var damageClass: DamageClass = DamageClass.PHYSICAL
    var learnMethod = LearnMethod.LEVEL
    var power = 0
    var acc = 0
    var pp = 0

    fun build(): LearnableMove {
        return LearnableMove(
            move = Move(
                id = id,
                name = name,
                typeIdentifier = typeIdentifier,
                damageClass = damageClass,
                power = power,
                acc = acc,
                pp = pp
            ),
            learnMethod = learnMethod
        )
    }
}

@PokemonMarker
class GrowthRateBuilder {

    var name: String = ""
    var expToMaxLevel = 0

    fun build(): GrowthRate {
        return GrowthRate(
            name = name,
            expToMaxLevel = expToMaxLevel,
        )
    }
}

@PokemonMarker
class TrainingBuilder {

    var catchRate = 0
    var baseHappiness = 0
    var baseExp = 0

    var effortYield = StatsBuilder().build()
    var growthRate = GrowthRateBuilder().build()

    fun effort(setup: StatsBuilder.() -> Unit) {
        val builder = StatsBuilder()
        builder.setup()
        effortYield = builder.build()
    }

    fun growthRate(setup: GrowthRateBuilder.() -> Unit) {
        val builder = GrowthRateBuilder()
        builder.setup()
        growthRate = builder.build()
    }

    fun build(): Training {
        return Training(
            effortYield = effortYield,
            catchRate = catchRate,
            growthRate = growthRate,
            baseHappiness = baseHappiness,
            baseExp = baseExp
        )
    }
}

@PokemonMarker
class EggGroupBuilder(private val id: Id) {

    var name: String = ""

    fun build(): EggGroup {
        return EggGroup(
            id = id,
            name = name,
        )
    }
}

@PokemonMarker
class BreedingBuilder() {

    var genderRatio = GenderRatio.GENDERLESS
    var eggCycles = 0

    private val eggGroups = mutableListOf<EggGroup>()

    fun eggGroup(id: Int, setup: EggGroupBuilder.() -> Unit) {
        val builder = EggGroupBuilder(Id(id))
        builder.setup()
        eggGroups += builder.build()
    }

    fun build(): Breeding {
        return Breeding(
            genderRatio = genderRatio,
            eggGroups = eggGroups,
            eggCycles = eggCycles
        )
    }
}

@PokemonMarker
class ImageUrlsBuilder {

    var officialArtwork: String? = null
    var frontDefault: String? = null
    var frontShiny: String? = null
    var backDefault: String? = null
    var backShiny: String? = null

    fun build(): ImageUrls {
        return ImageUrls(
            officialArtwork = officialArtwork.orEmpty(),
            frontDefault = frontDefault.orEmpty(),
            frontShiny = frontShiny.orEmpty(),
            backDefault = backDefault.orEmpty(),
            backShiny = backShiny.orEmpty(),
        )
    }
}

@PokemonMarker
class PokemonBuilder(private val id: Id) {

    var name: String? = null
    var genus: String = ""

    private val types = mutableListOf<Type>()
    private val abilities = mutableListOf<Ability>()
    private val learnableMoves = mutableListOf<LearnableMove>()

    private var species: Species = SpeciesBuilder().build()
    private var baseStats: Stats = StatsBuilder().build()
    private var training: Training = TrainingBuilder().build()
    private var breeding: Breeding = BreedingBuilder().build()
    private var imageUrls: ImageUrls = ImageUrlsBuilder().build()

    fun type(id: Int, setup: TypeBuilder.() -> Unit) {
        val builder = TypeBuilder(Id(id))
        builder.setup()
        types += builder.build()
    }

    fun ability(id: Int, setup: AbilityBuilder.() -> Unit) {
        val builder = AbilityBuilder(Id(id))
        builder.setup()
        abilities += builder.build()
    }

    fun species(setup: SpeciesBuilder.() -> Unit) {
        val builder = SpeciesBuilder()
        builder.setup()
        species = builder.build()
    }

    fun baseStats(setup: StatsBuilder.() -> Unit) {
        val builder = StatsBuilder()
        builder.setup()
        baseStats = builder.build()
    }

    fun move(id: Int, setup: LearnableMoveBuilder.() -> Unit) {
        val builder = LearnableMoveBuilder(Id(id))
        builder.setup()
        learnableMoves += builder.build()
    }

    fun training(setup: TrainingBuilder.() -> Unit) {
        val builder = TrainingBuilder()
        builder.setup()
        training = builder.build()
    }

    fun breeding(setup: BreedingBuilder.() -> Unit) {
        val builder = BreedingBuilder()
        builder.setup()
        breeding = builder.build()
    }

    fun imageUrls(setup: ImageUrlsBuilder.() -> Unit) {
        val builder = ImageUrlsBuilder()
        builder.setup()
        imageUrls = builder.build()
    }

    fun build(): Pokemon {
        return Pokemon(
            id = id,
            name = name.orEmpty(),
            genus = genus,
            types = types,
            abilities = abilities,
            species = species,
            baseStats = baseStats,
            learnableMoves = learnableMoves,
            training = training,
            breeding = breeding,
            imageUrls = imageUrls
        )
    }
}

@PokemonMarker
fun pokemon(id: Int, setup: PokemonBuilder.() -> Unit): Pokemon {
    val builder = PokemonBuilder(Id(id))
    builder.setup()
    return builder.build()
}