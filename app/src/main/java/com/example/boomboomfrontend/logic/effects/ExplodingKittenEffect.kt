package com.example.boomboomfrontend.logic.effects

import com.example.boomboomfrontend.logic.CardEffect
import com.example.boomboomfrontend.logic.GameManager
import com.example.boomboomfrontend.model.Player

class ExplodingKittenEffect : CardEffect {
    override fun apply (player: Player, gameManager: GameManager){
        if (player.hasDefuseCard()){
            player.useDefuseCard()
            println ("${player.name} defused an Exploding Kitten!")
        } else {
            gameManager.eliminatePlayer(player)
        }
    }
}