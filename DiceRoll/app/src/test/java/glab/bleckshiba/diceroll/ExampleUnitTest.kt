package glab.bleckshiba.diceroll

import glab.bleckshiba.diceroll.models.Dice
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun generateNumber() {
        val dice1 = Dice()
        val dice2 = Dice(8)
        val dice3 = Dice(12)

        val rollResult1: Int = dice1.roll()
        val rollResult2: Int = dice2.roll()
        val rollResult3: Int = dice3.roll()

        assert(rollResult1 in 1..6)
        assert(rollResult2 in 1..8)
        assert(rollResult3 in 1..12)
    }
}