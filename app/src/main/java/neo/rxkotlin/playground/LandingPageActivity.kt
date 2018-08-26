package neo.rxkotlin.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
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

    fun startOperatorsActivity(v: View) {
        OperatorsActivity::class.start(this)
    }
}
