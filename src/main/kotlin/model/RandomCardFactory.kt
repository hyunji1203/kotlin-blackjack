package model

import entity.Card
import entity.CardNumber
import entity.CardType
import entity.Cards

class RandomCardFactory : CardFactory {
    private val cards: Cards = CardType.values()
        .flatMap { type -> CardNumber.values().map { number -> Card(type, number) } }
        .shuffled()
        .toMutableList()
        .let { Cards(it) }

    private var index = 0

    private fun generateCard(): Card {
        require(index <= 52) { CARD_CREATE_INDEX_ERROR_MESSAGE }
        return cards.value[index++]
    }

    override fun generate(count: Int): Cards {
        return Cards((0 until count).map { generateCard() })
    }

    companion object {
        private const val CARD_CREATE_INDEX_ERROR_MESSAGE = "카드는 52개 이상 생성할 수 없습니다."
    }
}
