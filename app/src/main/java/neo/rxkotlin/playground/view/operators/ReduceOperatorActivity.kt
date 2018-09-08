package neo.rxkotlin.playground.view.operators

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.MaybeObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*
import neo.rxkotlin.playground.R
import neo.rxkotlin.playground.utility.appendText

/**
 * @author Naveen T P
 * @since 30/08/18
 */
class ReduceOperatorActivity : AppCompatActivity() {

    private val TAG = ReduceOperatorActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        displayInitialData()
        btn.setOnClickListener { doSomething() }
    }

    private fun doSomething() {
        Observable.just(1, 3, 5, 7, 9)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .reduce { t1: Int, t2: Int -> t1 + t2 }
                .subscribe(getObserver())

    }

    private fun getObserver(): MaybeObserver<Int> {
        return object : MaybeObserver<Int> {

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ${d.isDisposed}")
                tv_result.appendText("Applied reduce operator to add the numbers sequentially")
            }

            override fun onSuccess(t: Int) {
                tv_result.appendText("onSuccess $t")
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
        tv_explanation.appendText("The Reduce operator applies to each item emitted by an Observable, sequentially, and emit the final value - Returns a Maybe")
        tv_explanation.appendText("Initial items are: 1, 3, 5, 7, 9")
    }
}