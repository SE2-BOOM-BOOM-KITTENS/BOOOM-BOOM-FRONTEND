package com.example.boomboomfrontend.logic

import com.example.boomboomfrontend.model.*
import com.example.boomboomfrontend.model.basic.BlankCard
import com.example.boomboomfrontend.model.basic.DefuseCard
import com.example.boomboomfrontend.model.basic.ExplodingKittenCard
import kotlin.random.Random

class CardManager {

    private val deck: MutableList<Card> = mutableListOf()
    private val discardPile: MutableList<Card> = mutableListOf()

    fun initializeDeck(players: List<Player>) {
        deck.clear()

        // Sprint 1: Nur drei Kartentypen
        repeat(players.size - 1) { deck.add(ExplodingKittenCard()) }
        repeat(players.size) { deck.add(DefuseCard()) }
        repeat(10) { deck.add(BlankCard()) }

        deck.shuffle()
    }

    fun drawCard(): Card? {
        return if (deck.isNotEmpty()) deck.removeFirst() else null
    }

    fun playCard(card: Card, player: Player): Boolean {
        return if (player.hand.remove(card)) {
            discardPile.add(card)
            true
        } else false
    }

    fun returnCardToDeckAt(card: Card, position: Int) {
        val safePosition = position.coerceIn(0, deck.size) // falls Spieler Unsinn eingibt
        deck.add(safePosition, card)
    }

    fun deckSize(): Int = deck.size
}

