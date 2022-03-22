package glab.bleckshiba.diceroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import glab.bleckshiba.diceroll.models.Dice

class MainActivity : AppCompatActivity() {

    private lateinit var dice1: Dice
    private lateinit var dice2: Dice
    private lateinit var dice3: Dice

    private lateinit var txtDice1: TextView
    private lateinit var txtDice2: TextView
    private lateinit var txtDice3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtDice1 = findViewById(R.id.txt_dice1)
        txtDice2 = findViewById(R.id.txt_dice2)
        txtDice3 = findViewById(R.id.txt_dice3)

        val btnRoll: Button = findViewById(R.id.btnRoll)
        btnRoll.setOnClickListener { rollDice() }

        dice1 = Dice()
        dice2 = Dice(8)
        dice3 = Dice(10)

        setText()
    }

    private fun setText() {
        txtDice1.text = dice1.toString()
        txtDice2.text = dice2.toString()
        txtDice3.text = dice3.toString()
    }

    private fun rollDice() {
        dice1.roll()
        dice2.roll()
        dice3.roll()

        setText()
    }
}