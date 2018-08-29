package neo.rxkotlin.playground.operators

import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_example.*
import neo.rxkotlin.playground.BaseActivity
import neo.rxkotlin.playground.R
import neo.rxkotlin.playground.utility.appendText

/**
 * @author Naveen T P
 * @since 29/08/18
 */
class FilterExampleActivity : BaseActivity() {

    private val TAG = FilterExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        displayInitialData()
        btn.setOnClickListener { doSomething() }
    }

    private fun doSomething() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .filter { item -> item % 2 == 0 }
                .subscribe(getObserver())
    }

    private fun getObserver(): Observer<Int> {
        return object : Observer<Int> {

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ${d.isDisposed}")
                tv_result.appendText("Filtering only even numbers...")
            }

            override fun onNext(t: Int) {
                tv_result.appendText("onNext: $t")
            }

            override fun onComplete() {
                tv_result.appendText("onComplete")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError: ${e.message}")
            }
        }
    }

    private fun displayInitialData() {
        tv_explanation.appendText("The Filter operator filters an Observable by only " +
                "allowing items through that pass a test that you specify in the form of a predicate function..")
        tv_explanation.appendText("Initial items are: 1, 2, 3, 4, 5, 6")
    }


}