package neo.rxkotlin.playground.view.home

import android.os.Bundle
import android.view.View
import neo.rxkotlin.playground.BaseActivity
import neo.rxkotlin.playground.R
import neo.rxkotlin.playground.utility.start
import neo.rxkotlin.playground.view.observablesCreation.JustObservableActivity

/**
 * @author Naveen T P
 * @since 08/09/18
 */
class ObservablesCreationActivity : BaseActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_observables_creation)
        }

    fun startJustObservableActivity(v: View) = JustObservableActivity::class.start(this)
}