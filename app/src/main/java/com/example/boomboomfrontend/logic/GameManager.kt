package com.example.boomboomfrontend.logic

import com.example.boomboomfrontend.model.Card
import com.example.boomboomfrontend.model.Player
import com.example.boomboomfrontend.model.CardType

class GameManager(
    private val cardManager: CardManager,
    private val players: MutableList<Player>
) {

    var currentPlayer: Player? = null

    //Wird zu Beginn des Spielzugs aufgerufen
    fun startTurn(player: Player) {
        currentPlayer = player
        println("Welche Karte möchtest du spielen? (0 bis ${player.hand.lastIndex})")
        val index = readLine()?.toIntOrNull()

        if (index != null && index in player.hand.indices) {
            val card = player.hand[index]
            val success = cardManager.playCard(card, player, this)
            if (success) {
                println("Du spielst: ${card.name}")
            }
        } else {
            println("Keine Karte gespielt. Du kannst nun eine Karte ziehen.")
        }
    }

    //Wird aufgerufen, wenn Spieler auf Ziehstapel klickt
    fun endTurn() {
        val player = currentPlayer ?: return

        //Karte ziehen
        val drawn = cardManager.drawCard()

        val effect = CardEffectRegistry.getEffect(drawn.type)
        effect.apply(player, this)

        println("---- ${player.name}'s Zug wurde beendet ----")
        currentPlayer = null
    }

    fun returnCardToDeckAt(card: Card, position: Int) {
        cardManager.returnCardToDeckAt(card, position)
    }
}
