import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.example.boomboomfrontend.logic.*
import com.example.boomboomfrontend.model.*
import com.example.boomboomfrontend.model.basic.*

class CardManagementTest {

    private lateinit var gameManager: GameManager
    private lateinit var cardManager: CardManager
    private lateinit var players: MutableList<Player>
    private lateinit var testPlayer: Player

    @BeforeEach
    fun setup() {
        testPlayer = Player("Test", mutableListOf(BlankCard()))
        players = mutableListOf(testPlayer)
        cardManager = CardManager()
        cardManager.initializeDeck(players)
        gameManager = GameManager(cardManager, players)
    }

    @Test
    fun `drawCard returns null if deck is empty`() {
        // Leere das Deck komplett
        while (cardManager.drawCard() != null) {}

        val result = cardManager.drawCard()
        assertNull(result)
    }

    @Test
    fun `playCard removes from hand and adds to discardPile`() {
        val card = BlankCard()
        testPlayer.hand.add(card)

        val result = cardManager.playCard(card, testPlayer)

        assertTrue(result)
        assertFalse(testPlayer.hand.contains(card))
        assertTrue(cardManager.getDiscardPile().contains(card))
    }
}
