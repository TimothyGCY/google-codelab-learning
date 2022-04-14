package glab.bleckshiba.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import glab.bleckshiba.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /**
     * Old way with findViewById()
     * val myButton: Button = findViewById(R.id.myButton)
     * myButton.text = "A Button"
     *
     * Better way with view binding
     * val myButton: Button = binding.myButton
     * myButton.text = "A Button"
     *
     * Best way with view binding
     * binding.myButton.text = "A Button"
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateDisplayAmount(0.00)
        binding.btnCalculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val input = binding.edtCost.text
        if (input.isBlank()) {
            updateDisplayAmount(0.00)
            return
        }
        val cost: Double = input.toString().toDouble()
        val rate: Double = when (binding.rgRating.checkedRadioButtonId) {
            R.id.radAmazing -> 0.20
            R.id.radGood -> 0.18
            else -> 0.15
        }
        val roundUp: Boolean = binding.swRoundUp.isChecked
        var tip = cost * rate
        if (roundUp) {
            tip = ceil(tip)
        }
        updateDisplayAmount(tip)
    }

    private fun updateDisplayAmount(value: Double) {
        val formattedText = NumberFormat.getCurrencyInstance().format(value)
        binding.txtTipAmount.text = getString(R.string.tip_amount, formattedText)
    }
}