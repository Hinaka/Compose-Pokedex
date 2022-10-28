package dev.hinaka.pokedex.domain.pokemon

import dev.hinaka.pokedex.domain.Ability
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.common.DomainDslMarker
import dev.hinaka.pokedex.domain.move.DamageClass
import dev.hinaka.pokedex.domain.move.LearnMethod
import dev.hinaka.pokedex.domain.move.LearnableMove
import dev.hinaka.pokedex.domain.move.Move
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Abilities
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Breeding.GenderRatio.GENDERLESS
import dev.hinaka.pokedex.domain.pokemon.Pokemon.ImageUrls
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Species
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Species.Height
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Species.Weight
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Stats
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Training
import dev.hinaka.pokedex.domain.pokemon.Pokemon.Training.GrowthRate
import dev.hinaka.pokedex.domain.type.Type
import dev.hinaka.pokedex.domain.type.Type.Identifier.GRASS
import dev.hinaka.pokedex.domain.type.TypeIdentifier
import dev.hinaka.pokedex.domain.type.TypeIdentifier.UNKNOWN

@DomainDslMarker
class TypeBuilder(private val id: Id) {

    var identifier: Type.Identifier? = null
    var name: String? = null

    fun build(): Type {
        return Type(
            id = id,
            identifier = identifier ?: GRASS, //TODO: use other default value
            name = name.orEmpty()
        )
    }
}

@DomainDslMarker
class AbilityBuilder(private val id: Id) {

    var name: String? = null
    var effect: String? = null

    fun build(): Ability {
        return Ability(
            id = id,
            name = name.orEmpty(),
            effect = effect.orEmpty()
        )
    }
}

@DomainDslMarker
class AbilitiesBuilder {

    private val normalAbilities = mutableListOf<Ability>()
    private var hiddenAbility: Ability? = null

    fun normal(id: Int, setup: AbilityBuilder.() -> Unit) {
        val builder = AbilityBuilder(Id(id))
        builder.setup()
        normalAbilities += builder.build()
    }

    fun hidden(id: Int, setup: AbilityBuilder.() -> Unit) {
        val builder = AbilityBuilder(Id(id))
        builder.setup()
        hiddenAbility = builder.build()
    }

    fun build(): Abilities {
        return Abilities(
            normalAbilities = normalAbilities,
            hiddenAbility = hiddenAbility
        )
    }
}

@DomainDslMarker
class SpeciesBuilder {

    var flavorText: String? = null
    var heightInDecimeter: Int? = null
    var weightInHectogram: Int? = null

    fun build(): Species {
        return Species(
            flavorText = flavorText.orEmpty(),
            height = Height.decimeter(heightInDecimeter ?: 0),
            weight = Weight.hectogram(weightInHectogram ?: 0)
        )
    }
}

@DomainDslMarker
class StatsBuilder {

    var hp: Int? = null
    var attack: Int? = null
    var defense: Int? = null
    var spAttack: Int? = null
    var spDefense: Int? = null
    var speed: Int? = null

    fun build(): Stats {
        return Stats(
            hp = hp ?: 0,
            attack = attack ?: 0,
            defense = defense ?: 0,
            specialAttack = spAttack ?: 0,
            specialDefense = spDefense ?: 0,
            speed = speed ?: 0,
        )
    }
}

@DomainDslMarker
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

@DomainDslMarker
class GrowthRateBuilder {

    var name: String? = null
    var expToMaxLevel: Int? = null

    fun build(): GrowthRate {
        return GrowthRate(
            name = name.orEmpty(),
            expToMaxLevel = expToMaxLevel ?: 0,
        )
    }
}

@DomainDslMarker
class TrainingBuilder {

    var catchRate: Int? = null
    var baseHappiness: Int? = null
    var baseExp: Int? = null

    private var effortYield = StatsBuilder().build()
    private var growthRate = GrowthRateBuilder().build()

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
            catchRate = catchRate ?: 0,
            baseExp = baseExp ?: 0,
            baseHappiness = baseHappiness ?: 0,
            effortYield = effortYield,
            growthRate = growthRate
        )
    }
}

@DomainDslMarker
class BreedingBuilder() {

    var genderRatio: GenderRatio? = null
    var eggCycles: Int? = null

    private val eggGroups = mutableListOf<EggGroup>()

    fun eggGroup(id: Int, setup: EggGroupBuilder.() -> Unit) {
        val builder = EggGroupBuilder(Id(id))
        builder.setup()
        eggGroups += builder.build()
    }

    fun build(): Breeding {
        return Breeding(
            genderRatio = genderRatio ?: GENDERLESS,
            eggGroups = eggGroups,
            eggCycles = eggCycles ?: 0
        )
    }
}

@DomainDslMarker
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

@DomainDslMarker
class PokemonBuilder(private val id: Id) {

    var name: String? = null
    var genus: String? = null

    private val types = mutableListOf<Type>()
    private val learnableMoves = mutableListOf<LearnableMove>()

    private var abilities: Abilities = AbilitiesBuilder().build()
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

    fun abilities(setup: AbilitiesBuilder.() -> Unit) {
        val builder = AbilitiesBuilder()
        builder.setup()
        abilities = builder.build()
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
            genus = genus.orEmpty(),
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

@DomainDslMarker
fun pokemon(id: Int, setup: PokemonBuilder.() -> Unit): Pokemon {
    val builder = PokemonBuilder(Id(id))
    builder.setup()
    return builder.build()
}