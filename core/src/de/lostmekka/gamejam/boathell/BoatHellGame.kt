package de.lostmekka.gamejam.boathell

import com.badlogic.gdx.Screen
import ktx.app.KtxGame

class BoatHellGame : KtxGame<Screen>() {
    override fun create() {
        addScreen(GamePlayScreen())
        setScreen<GamePlayScreen>()
    }
}
