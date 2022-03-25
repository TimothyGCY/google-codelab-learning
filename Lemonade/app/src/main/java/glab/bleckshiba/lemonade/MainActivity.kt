package glab.bleckshiba.lemonade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val LEMONADE_STATE = "LEMONADE_STATE"
    private val LEMON_SIZE = "LEMON_SIZE"
    private val SQUEEZE_COUNT = "SQUEEZE_COUNT"

    // SELECT represents the "pick lemon" state
    private val SELECT = "select"

    // SQUEEZE represents the "squeeze lemon" state
    private val SQUEEZE = "squeeze"

    // DRINK represents the "drink lemonade" state
    private val DRINK = "drink"

    // RESTART represents the state where the lemonade has been drunk and the glass is empty
    private val RESTART = "restart"

    // Default state is select
    private var lemonadeState = SELECT
    private var lemonSize = -1
    private var squeezeCount = -1

    private var lemonTree = LemonTree()
    private var lemonImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, SELECT)
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }

        lemonImage = findViewById(R.id.image_lemon_state)
        setViewElements()
        lemonImage!!.setOnClickListener { clickLemonImage() }
        lemonImage!!.setOnLongClickListener {
            if (lemonadeState == SQUEEZE)
                clickLemonImage()
            false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LEMONADE_STATE, lemonadeState)
        outState.putInt(LEMON_SIZE, lemonSize)
        outState.putInt(SQUEEZE_COUNT, squeezeCount)
        super.onSaveInstanceState(outState)
    }

    // TODO: OPTIMIZE THIS SHIT
    private fun clickLemonImage() {
        lemonadeState = when (lemonadeState) {
            SELECT -> SQUEEZE
            SQUEEZE -> if (squeezeCount == lemonSize) DRINK else SQUEEZE
            DRINK -> RESTART
            else -> SELECT
        }

        if (lemonadeState == SQUEEZE) {
            if (squeezeCount == -1) {
                squeezeCount = 0
                lemonSize = lemonTree.pick()
            } else {
                squeezeCount++
                showSnackbar()
            }
        } else if (lemonadeState == DRINK) {
            squeezeCount = -1
            lemonSize = -1
        }

        setViewElements()
    }

    private fun setViewElements() {
        val textAction: TextView = findViewById(R.id.text_action)
        // TODO: Setup up a conditional that tracks the lemonadeState

        // TODO: foreach state, the textAction TextView should be set to the corresponding string
        //  from the string resource file.
        textAction.setText(
            when (lemonadeState) {
                SELECT -> R.string.lemon_select
                SQUEEZE -> R.string.lemon_squeeze
                DRINK -> R.string.lemon_drink
                else -> R.string.lemon_empty_glass
            }
        )

        // TODO: Additionally, for each state, the lemonImage should be set to the corresponding
        //  drawable from the drawable resource file.
        lemonImage!!.setImageResource(
            when (lemonadeState) {
                SELECT -> R.drawable.lemon_tree
                SQUEEZE -> R.drawable.lemon_squeeze
                DRINK -> R.drawable.lemon_drink
                else -> R.drawable.lemon_restart
            }
        )
    }

    private fun showSnackbar(): Boolean {
        if (lemonadeState != SQUEEZE) return false;
        val squeezeText = getString(R.string.squeeze_count, squeezeCount)
        Snackbar.make(
            findViewById(R.id.constraint_layout),
            squeezeText,
            Snackbar.LENGTH_SHORT
        ).show()
        return true
    }
}