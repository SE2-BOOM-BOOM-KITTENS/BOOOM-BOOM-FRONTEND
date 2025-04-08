package com.example.boomboomfrontend

import com.example.boomboomfrontend.model.ConnectionStatus
import com.example.boomboomfrontend.model.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `player starts alive and has defuse`(){
        val player = Player (id = 1.toString(), name = "Player1", defuseCount = 1, isAlive = true, status = ConnectionStatus.JOINED)

        val hasCard = player.hasDefuseCard()

        assertTrue (player.isAlive)
        assertTrue (hasCard)
        assertEquals (1, player.defuseCount)
    }

    @Test
    fun `useDefuseCard decreases count`(){
        val player = Player (id = 2.toString(), name = "Player2", defuseCount = 2, isAlive = true, status = ConnectionStatus.JOINED)

        val used = player.useDefuseCard()

        assertTrue (used)
        assertEquals (1, player.defuseCount)
    }

    @Test
    fun `useDefuseCard fails when none available`(){
        val player = Player (id = 3.toString(), name = "Player3", defuseCount = 0, isAlive = true, status = ConnectionStatus.JOINED)

        val used = player.useDefuseCard()

        assertFalse (used)
        assertFalse (player.hasDefuseCard())
        assertEquals (0, player.defuseCount)
    }

    @Test
    fun `hasDefuseCard fails when none available`(){
        val player = Player (id = 4.toString(), name = "Player4", defuseCount = 0, isAlive = true, status = ConnectionStatus.JOINED)

        val hasCard = player.hasDefuseCard()

        assertFalse (hasCard)
        assertEquals (0, player.defuseCount)
    }

    @Test
    fun `addDefuseCard increases count`(){
        val player = Player (id = 5.toString(), name = "Player5", defuseCount = 0, isAlive = true, status = ConnectionStatus.JOINED)

        player.addDefuseCard()

        assertEquals (1, player.defuseCount)
    }


}