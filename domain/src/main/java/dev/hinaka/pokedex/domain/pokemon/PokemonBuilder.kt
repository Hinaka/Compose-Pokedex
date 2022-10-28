package dev.hinaka.pokedex.domain.pokemon

import dev.hinaka.pokedex.domain.Ability
import dev.hinaka.pokedex.domain.Id
import dev.hinaka.pokedex.domain.common.DomainBuilder
import dev.hinaka.pokedex.domain.common.build
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

fun pokemon(id: Int, init: PokemonBuilder.() -> Unit): Pokemon = build(PokemonBuilder(id), init)

class TypeBuilder(private val id: Int) : DomainBuilder<Type> {

    var identifier: Type.Identifier? = null
    var name: String? = null

    override fun build(): Type {
        return Type(
            id = Id(id),
            identifier = identifier ?: GRASS, //TODO: use other default value
            name = name.orEmpty()
        )
    }
}

class AbilityBuilder(private val id: Int) : DomainBuilder<Ability> {

    var name: String? = null
    var effect: String? = null

    override fun build(): Ability {
        return Ability(
            id = Id(id),
            name = name.orEmpty(),
            effect = effect.orEmpty()
        )
    }
}

class AbilitiesBuilder : DomainBuilder<Abilities> {

    private val normalAbilities = mutableListOf<Ability>()
    private var hiddenAbility: Ability? = null

    fun normal(id: Int, init: AbilityBuilder.() -> Unit) {
        normalAbilities += build(AbilityBuilder(id), init)
    }

    fun hidden(id: Int, init: AbilityBuilder.() -> Unit) {
        hiddenAbility = build(AbilityBuilder(id), init)
    }

    override fun build(): Abilities {
        return Abilities(
            normalAbilities = normalAbilities,
            hiddenAbility = hiddenAbility
        )
    }
}

class SpeciesBuilder : DomainBuilder<Species> {

    var flavorText: String? = null
    var heightInDecimeter: Int? = null
    var weightInHectogram: Int? = null

    override fun build(): Species {
        return Species(
            flavorText = flavorText.orEmpty(),
            height = Height.decimeter(heightInDecimeter ?: 0),
            weight = Weight.hectogram(weightInHectogram ?: 0)
        )
    }
}

class StatsBuilder : DomainBuilder<Stats> {

    var hp: Int? = null
    var attack: Int? = null
    var defense: Int? = null
    var spAttack: Int? = null
    var spDefense: Int? = null
    var speed: Int? = null

    override fun build(): Stats {
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

class LearnableMoveBuilder(private val id: Int) : DomainBuilder<LearnableMove> {

    var name: String = ""
    var typeIdentifier: TypeIdentifier = UNKNOWN
    var damageClass: DamageClass = DamageClass.PHYSICAL
    var learnMethod = LearnMethod.LEVEL
    var power = 0
    var acc = 0
    var pp = 0

    override fun build(): LearnableMove {
        return LearnableMove(
            move = Move(
                id = Id(id),
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

class GrowthRateBuilder : DomainBuilder<GrowthRate> {

    var name: String? = null
    var expToMaxLevel: Int? = null

    override fun build(): GrowthRate {
        return GrowthRate(
            name = name.orEmpty(),
            expToMaxLevel = expToMaxLevel ?: 0,
        )
    }
}

class TrainingBuilder : DomainBuilder<Training> {

    var catchRate: Int? = null
    var baseHappiness: Int? = null
    var baseExp: Int? = null

    private var effortYield = StatsBuilder().build()
    private var growthRate = GrowthRateBuilder().build()

    fun effort(init: StatsBuilder.() -> Unit) {
        effortYield = build(StatsBuilder(), init)
    }

    fun growthRate(init: GrowthRateBuilder.() -> Unit) {
        growthRate = build(GrowthRateBuilder(), init)
    }

    override fun build(): Training {
        return Training(
            catchRate = catchRate ?: 0,
            baseExp = baseExp ?: 0,
            baseHappiness = baseHappiness ?: 0,
            effortYield = effortYield,
            growthRate = growthRate
        )
    }
}

class BreedingBuilder : DomainBuilder<Breeding> {

    var genderRatio: GenderRatio? = null
    var eggCycles: Int? = null

    private val eggGroups = mutableListOf<EggGroup>()

    fun eggGroup(id: Int, init: EggGroupBuilder.() -> Unit) {
        eggGroups += build(EggGroupBuilder(id), init)
    }

    override fun build(): Breeding {
        return Breeding(
            genderRatio = genderRatio ?: GENDERLESS,
            eggGroups = eggGroups,
            eggCycles = eggCycles ?: 0
        )
    }
}

class ImageUrlsBuilder : DomainBuilder<ImageUrls> {

    var officialArtwork: String? = null
    var frontDefault: String? = null
    var frontShiny: String? = null
    var backDefault: String? = null
    var backShiny: String? = null

    override fun build(): ImageUrls {
        return ImageUrls(
            officialArtwork = officialArtwork.orEmpty(),
            frontDefault = frontDefault.orEmpty(),
            frontShiny = frontShiny.orEmpty(),
            backDefault = backDefault.orEmpty(),
            backShiny = backShiny.orEmpty(),
        )
    }
}

class PokemonBuilder(private val id: Int) : DomainBuilder<Pokemon> {

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

    fun type(id: Int, init: TypeBuilder.() -> Unit) {
        types += build(TypeBuilder(id), init)
    }

    fun abilities(init: AbilitiesBuilder.() -> Unit) {
        abilities = build(AbilitiesBuilder(), init)
    }

    fun species(init: SpeciesBuilder.() -> Unit) {
        species = build(SpeciesBuilder(), init)
    }

    fun baseStats(init: StatsBuilder.() -> Unit) {
        baseStats = build(StatsBuilder(), init)
    }

    fun move(id: Int, init: LearnableMoveBuilder.() -> Unit) {
        learnableMoves += build(LearnableMoveBuilder(id), init)
    }

    fun training(init: TrainingBuilder.() -> Unit) {
        training = build(TrainingBuilder(), init)
    }

    fun breeding(init: BreedingBuilder.() -> Unit) {
        breeding = build(BreedingBuilder(), init)
    }

    fun imageUrls(init: ImageUrlsBuilder.() -> Unit) {
        imageUrls = build(ImageUrlsBuilder(), init)
    }

    override fun build(): Pokemon {
        return Pokemon(
            id = Id(id),
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