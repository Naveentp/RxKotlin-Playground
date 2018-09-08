package neo.rxkotlin.playground.view.home

import android.os.Bundle
import android.view.View
import neo.rxkotlin.playground.BaseActivity
import neo.rxkotlin.playground.R
import neo.rxkotlin.playground.utility.start
import neo.rxkotlin.playground.view.operators.*

/**
 * @author Naveen T P
 * @since 25/08/18
 */

class OperatorsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operators)
    }

    fun startMapExampleActivity(v: View) = MapOperatorActivity::class.start(this)

    fun startZipExampleActivity(v: View) = ZipOperatorActivity::class.start(this)

    fun startFilterExampleActivity(v: View) = FilterOperatorActivity::class.start(this)

    fun startTakeExampleActivity(v: View) = TakeOperatorActivity::class.start(this)

    fun startReduceExampleActivity(v: View) = ReduceOperatorActivity::class.start(this)

    fun startSkipExampleActivity(v: View) = SkipOperatorActivity::class.start(this)

    fun startFlatMapExampleActivity(v: View) = FlatMapOperatorActivity::class.start(this)

    fun startDistinctExampleActivity(v: View) = DistinctOperatorActivity::class.start(this)

}