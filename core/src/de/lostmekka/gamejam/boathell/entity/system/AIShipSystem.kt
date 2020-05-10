package de.lostmekka.gamejam.boathell.entity.system

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.utils.ImmutableArray
import de.lostmekka.gamejam.boathell.entity.component.AIShipComponent
import de.lostmekka.gamejam.boathell.entity.component.MovementStrategyContext
import de.lostmekka.gamejam.boathell.entity.component.PlayerControlledComponent
import de.lostmekka.gamejam.boathell.entity.component.PositionComponent
import ktx.ashley.allOf

class AIShipSystem : BaseSystem() {
    private lateinit var playerEntities: ImmutableArray<Entity>

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        playerEntities = engine.getEntitiesFor(allOf(PlayerControlledComponent::class, PositionComponent::class).get())
    }

    override fun update(deltaTime: Float) {
        for (entity in entities) {
            val pos = PositionComponent.mapper[entity]
            val mov = AIShipComponent.mapper[entity]
            val playerPos = playerEntities
                .firstOrNull()
                ?.let { PositionComponent.mapper[it] }
                ?: PositionComponent(0f, 0f, 0f)
            mov.movementStrategy(MovementStrategyContext(playerPos, pos))
        }
    }

    override fun familyBuilder() = allOf(
        AIShipComponent::class,
        PositionComponent::class
    )
}
