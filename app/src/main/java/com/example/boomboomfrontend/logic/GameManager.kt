package com.example.boomboomfrontend.logic

import com.example.boomboomfrontend.model.Player

class GameManager {

    private val players = mutableListOf<Player>()
    var currentPlayerIndex = 0

    fun addPlayer (player: Player){
        players.add(player)
    }

    fun eliminatePlayer (player:Player){
        player.isAlive = false
        println ("${player.name} has been eliminated!")
    }

    fun getCurrentPlayer(): Player {
        return players[currentPlayerIndex]
    }

}



