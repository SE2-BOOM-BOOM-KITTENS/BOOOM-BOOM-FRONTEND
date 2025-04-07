package com.example.boomboomfrontend.logic

import com.example.boomboomfrontend.model.CardType
import com.example.boomboomfrontend.logic.effects.*

object CardEffectRegistry {
    private val effects = mapOf(
        CardType.BLANK to BlankEffect(),
        CardType.DEFUSE to DefuseEffect(),
        CardType.EXPLODING_KITTEN  to ExplodingKittenEffect()
    )

    fun getEffect (cardType: CardType): CardEffect{
        return effects [cardType]
            ?: throw IllegalArgumentException ("No effect registered for $cardType")
    }

}