package neo.rxkotlin.playground.operators

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_example.*
import neo.rxkotlin.playground.R
import neo.rxkotlin.playground.utility.appendText

/**
 * @author Naveen T P
 * @since 30/08/18
 */
class TakeExampleActivity : AppCompatActivity() {

    private val TAG = TakeExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        displayInitialData()
        btn.setOnClickListener { doSomething() }
    }

    private fun doSomething() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .take(3)
                .subscribe(getObserver())
    }

    private fun getObserver(): Observer<Int> {
        return object : Observer<Int> {

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ${d.isDisposed}")
                tv_result.appendText("Taking only first 3 integers")
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
        tv_explanation.appendText("The Take operator emit only the first n items emitted by an Observable")
        tv_explanation.appendText("Initial items are: 1, 2, 3, 4, 5, 6")
    }
}