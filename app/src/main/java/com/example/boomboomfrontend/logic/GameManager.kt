package com.example.boomboomfrontend.logic

import com.example.boomboomfrontend.model.*

class GameManager(
    private val cardManager: CardManager,
    private val players: MutableList<Player>
) {

    var currentPlayer: Player? = null

    //Wird zu Beginn des Spielzugs aufgerufen
    fun startTurn(player: Player) {
        currentPlayer = player
        println("---- ${player.name}'s Zug hat begonnen ----")
        println("Welche Karte möchtest du spielen? (0 bis ${player.hand.lastIndex})")
        val index = readLine()?.toIntOrNull()

        if (index != null && index in player.hand.indices) {
            val card = player.hand[index]
            val success = cardManager.playCard(card, player)
            if (success) {
                println("Du spielst: ${card.name}")
                card.play(this, player)
            }
        } else {
            println("Ungültige Eingabe.")
        }
    }

    //Wird aufgerufen, wenn Spieler auf Ziehstapel klickt
    fun endTurn() {
        val player = currentPlayer ?: return

        //Karte ziehen
        val drawn = cardManager.drawCard()

        handleDrawnCard(drawn, player)

        println("---- ${player.name}'s Zug wurde beendet ----")
        currentPlayer = null
    }

    //Falls Exploding Kittens gezogen wird - noch anzupassen
    private fun handleDrawnCard(card: Card, player: Player) {

    }

    fun returnCardToDeckAt(card: Card, position: Int) {
        cardManager.returnCardToDeckAt(card, position)
    }
}
