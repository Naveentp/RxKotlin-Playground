package neo.rxkotlin.playground.view.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import neo.rxkotlin.playground.R
import neo.rxkotlin.playground.utility.start

/**
 * @author Naveen T P
 * @since 25/08/18
 */

class LandingPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
    }

    fun startObservablesCreationActivity(v: View) = ObservablesCreationActivity::class.start(this)

    fun startOperatorsActivity(v: View) = OperatorsActivity::class.start(this)
}
