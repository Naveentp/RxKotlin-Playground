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
 * @since 31/08/18
 */
class SkipExampleActivity : AppCompatActivity() {

    private val TAG = SkipExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        displayInitialData()
        btn.setOnClickListener { doSomething() }
    }

    private fun doSomething() {
        Observable.just("One", "Two", "Three", "Four", "Five", "Six")
                .skip(3)
                .subscribe(getObserver())
    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ${d.isDisposed}")
                tv_result.appendText("Skipping first 3 items..")
            }

            override fun onNext(t: String) {
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
        tv_explanation.appendText("The Skip operator suppress the first n items emitted by an Observable")
        tv_explanation.appendText("Initial items are: \"One\", \"Two\", \"Three\", \"Four\", \"Five\", \"Six\"")
    }
}