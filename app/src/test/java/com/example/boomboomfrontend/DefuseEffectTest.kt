package com.example.boomboomfrontend

import com.example.boomboomfrontend.logic.effects.DefuseEffect
import com.example.boomboomfrontend.logic.GameManager
import com.example.boomboomfrontend.model.ConnectionStatus
import com.example.boomboomfrontend.model.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DefuseEffectTest {

    @Test
    fun `defuse effect decreases count`(){
        val player = Player (id = "1", name = "Player1", defuseCount = 1, isAlive = true, status = ConnectionStatus.JOINED)
        val effect = DefuseEffect()
        val gameManager = GameManager()

        effect.apply(player, gameManager)

        assertEquals(0, player.defuseCount)
    }

}