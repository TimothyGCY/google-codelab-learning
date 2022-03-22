package glab.bleckshiba.diceroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import glab.bleckshiba.diceroll.models.Dice

class MainActivity : AppCompatActivity() {

    private lateinit var dice1: Dice
    private lateinit var dice2: Dice
    private lateinit var dice3: Dice

    private var dice1Value = R.drawable.dice_1

    private lateinit var ivDice1: ImageView
    private lateinit var txtDice2: TextView
    private lateinit var txtDice3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivDice1 = findViewById(R.id.iv_dice1)
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
        ivDice1.setImageResource(dice1Value)
        txtDice2.text = dice2.toString()
        txtDice3.text = dice3.toString()
    }

    private fun rollDice() {
        when (dice1.roll()) {
            1 -> dice1Value = R.drawable.dice_1
            2 -> dice1Value = R.drawable.dice_2
            3 -> dice1Value = R.drawable.dice_3
            4 -> dice1Value = R.drawable.dice_4
            5 -> dice1Value = R.drawable.dice_5
            6 -> dice1Value = R.drawable.dice_6
        }
        dice2.roll()
        dice3.roll()

        setText()
    }
}