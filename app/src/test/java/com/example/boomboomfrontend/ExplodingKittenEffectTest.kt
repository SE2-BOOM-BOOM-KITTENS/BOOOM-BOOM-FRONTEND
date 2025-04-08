package com.example.boomboomfrontend

import com.example.boomboomfrontend.logic.effects.ExplodingKittenEffect
import com.example.boomboomfrontend.logic.GameManager
import com.example.boomboomfrontend.model.ConnectionStatus
import com.example.boomboomfrontend.model.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ExplodingKittenEffectTest {

    @Test
    fun `exploding kitten kills player without defuse`(){
        val player = Player (id = "1", name = "Player1", defuseCount = 0, isAlive = true, status = ConnectionStatus.JOINED)
        val effect = ExplodingKittenEffect()
        val gameManager = GameManager()

        effect.apply(player, gameManager)

        assertFalse(player.isAlive)
    }

    @Test
    fun `exploding kitten defused when player has defuse card`(){
        val player = Player (id = "2", name = "Player2", defuseCount = 1, isAlive = true, status = ConnectionStatus.JOINED)
        val effect = ExplodingKittenEffect()
        val gameManager = GameManager()

        effect.apply(player, gameManager)

        assertTrue(player.isAlive)
        assertEquals(0, player.defuseCount)
    }

}