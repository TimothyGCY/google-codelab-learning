package glab.bleckshiba.diceroll.models

class Dice(private val side: Int = 6) {
    private var diceRange: IntRange = 1..side
    private var value: Int = 1

    fun roll(): Int {
        value = diceRange.random()
        return value
    }

    override fun toString(): String {
        return "Your dice with $side side rolled ${this.value}"
    }
}