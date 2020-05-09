package de.lostmekka.gamejam.boathell.entity

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import de.lostmekka.gamejam.boathell.asset.Textures
import de.lostmekka.gamejam.boathell.entity.component.PositionComponent
import de.lostmekka.gamejam.boathell.entity.component.ProjectileMovementComponent
import de.lostmekka.gamejam.boathell.entity.component.SpriteComponent
import de.lostmekka.gamejam.boathell.entity.component.WeaponComponent
import de.lostmekka.gamejam.boathell.entity.component.WeaponTriggerStrategy

object Weapons {
    fun addWeapon(
        engine: Engine,
        offsetX: Float,
        offsetY: Float,
        offsetAngle: Float,
        cooldownTime: Float,
        projectileInit: WeaponTriggerStrategy
    ) = engine.addEntityWithComponents(
        PositionComponent(0f, 0f, 0f), // will be auto set by weapon owner system
        WeaponComponent(cooldownTime, offsetX, offsetY, offsetAngle, projectileInit)
        // TODO: add sprite
    )
}

object WeaponTriggerStrategies {
    val boring: WeaponTriggerStrategy = {
        engine.addEntityWithComponents(
            PositionComponent(x, y, angle),
            ProjectileMovementComponent(
                maxLifeTime = 4f,
                movementStrategy = ProjectileMovementStrategies.straight(angle, 1f)
            )
        )
    }
}