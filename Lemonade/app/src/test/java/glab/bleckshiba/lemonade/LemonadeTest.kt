package glab.bleckshiba.lemonade

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test

import org.junit.runner.RunWith
import androidx.test.filters.LargeTest
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class LemonadeTest : BaseTest() {

    @Before
    fun setup() {
        launchActivity<MainActivity>()
    }

    /**
     * Test the view component of the pick lemon state
     */
    @Test
    fun `test_initial_state`() {
        testState(R.string.lemon_select, R.drawable.lemon_tree)
    }

    /**
     * Test that the picked lemon functionality takes us to the "squeeze state"
     */
    @Test
    fun `test_picking_lemon_proceeds_to_squeeze_state`() {
        onView(withId(R.id.image_lemon_state)).perform(click())
        testState(R.string.lemon_squeeze, R.drawable.lemon_drink)
    }

    /**
     * Test that the squeeze functionality takes us to the "drink state"
     */
    fun `test_squeezing_lemon_proceeds_to_drink_state`() {
        onView(withId(R.id.image_lemon_state)).perform(click())
        juiceLemon()
        testState(R.string.lemon_drink, R.drawable.lemon_drink)
    }

    /**
     * Test squeeze count SnackBar
     */
    fun `test_squeeze_count_snackbar_is_displayed`() {
        // Click image to progress state
        onView(withId(R.id.image_lemon_state)).perform(click())
        // Click image to progress state
        onView(withId(R.id.image_lemon_state)).perform(click())
        // Long click image to progress state
        onView(withId(R.id.image_lemon_state)).perform(longClick())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Squeeze count: 1, keep squeezing!")))
    }

    /**
     * Test that the drink functionality takes us to the "restart state"
     */
    fun `test_drinking_Juice_proceeds_to_restart_state`() {
        // Click image to progress state
        onView(withId(R.id.image_lemon_state)).perform(click())
        juiceLemon()
        onView(withId(R.id.image_lemon_state)).perform(click())
        testState(R.string.lemon_empty_glass, R.drawable.lemon_restart)
    }

    /**
     * Test that the restart functionality takes us back to the "pick lemon state"
     */
    fun `test_restarting_proceeds_to_pick_lemon_state`() {
        // Click image to progress state
        onView(withId(R.id.image_lemon_state)).perform(click())
        juiceLemon()
        // Click image to progress state
        onView(withId(R.id.image_lemon_state)).perform(click())
        // Click image to progress state
        onView(withId(R.id.image_lemon_state)).perform(click())
        testState(R.string.lemon_select, R.drawable.lemon_tree)
    }
}