package com.example.boomboomfrontend.logic.effects

import com.example.boomboomfrontend.logic.CardEffect
import com.example.boomboomfrontend.logic.GameManager
import com.example.boomboomfrontend.model.Player

class BlankEffect : CardEffect {
    override fun apply (player: Player, gameManager: GameManager){
        println ("Blank card played. Nothing happens.")
    }
}