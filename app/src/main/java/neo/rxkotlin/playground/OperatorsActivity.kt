package neo.rxkotlin.playground

import android.os.Bundle
import android.view.View
import neo.rxkotlin.playground.operators.*
import neo.rxkotlin.playground.utility.start

/**
 * @author Naveen T P
 * @since 25/08/18
 */

class OperatorsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operators)
    }

    fun startSimpleExampleActivity(v: View) = SimpleExampleActivity::class.start(this)

    fun startMapExampleActivity(v: View) = MapExampleActivity::class.start(this)

    fun startZipExampleActivity(v: View) = ZipExampleActivity::class.start(this)

    fun startFilterExampleActivity(v: View) = FilterExampleActivity::class.start(this)

    fun startTakeExampleActivity(v: View) = TakeExampleActivity::class.start(this)

    fun startReduceExampleActivity(v: View) = ReduceExampleActivity::class.start(this)

}