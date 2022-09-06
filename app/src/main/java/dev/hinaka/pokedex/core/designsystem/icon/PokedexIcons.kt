package dev.hinaka.pokedex.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import dev.hinaka.pokedex.R

object PokedexIcons {
    val TypeBug = R.drawable.ic_type_bug
    val TypeDark = R.drawable.ic_type_dark
    val TypeDragon = R.drawable.ic_type_dragon
    val TypeElectric = R.drawable.ic_type_electric
    val TypeFairy = R.drawable.ic_type_fairy
    val TypeFighting = R.drawable.ic_type_fighting
    val TypeFire = R.drawable.ic_type_fire
    val TypeFlying = R.drawable.ic_type_flying
    val TypeGhost = R.drawable.ic_type_ghost
    val TypeGrass = R.drawable.ic_type_grass
    val TypeGround = R.drawable.ic_type_ground
    val TypeIce = R.drawable.ic_type_ice
    val TypeNormal = R.drawable.ic_type_normal
    val TypePoison = R.drawable.ic_type_poison
    val TypePsychic = R.drawable.ic_type_psychic
    val TypeRock = R.drawable.ic_type_rock
    val TypeSteel = R.drawable.ic_type_steel
    val TypeWater = R.drawable.ic_type_water
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}