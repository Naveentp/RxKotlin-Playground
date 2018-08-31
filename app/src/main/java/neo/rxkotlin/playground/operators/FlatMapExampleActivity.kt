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
class FlatMapExampleActivity : AppCompatActivity() {

    private val TAG = FlatMapExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        displayInitialData()
        btn.setOnClickListener { doSomething() }
    }

    private fun doSomething() {
        getObservable()
                .flatMap { integer -> getAddObservable(integer, integer) }
                .flatMap { integer -> getMultiplyObservable(integer, integer) }
                .subscribe(getObserver())
    }

    private fun getObservable(): Observable<Int> {
        return Observable.just(1, 2, 3, 4, 5)
    }

    private fun getAddObservable(first: Int, second: Int): Observable<Int> {
        return Observable.just(first + second)
    }

    private fun getMultiplyObservable(first: Int, second: Int): Observable<Int> {
        return Observable.just(first * second)
    }

    private fun getObserver(): Observer<Int> {
        return object : Observer<Int> {

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ${d.isDisposed}")
            }

            override fun onNext(t: Int) {
                tv_result.appendText("onNext: $t")
            }

            override fun onComplete() {
                tv_result.appendText("onComplete")
            }

            override fun onError(e: Throwable) {
                tv_result.appendText("onError: ${e.message}")
            }

        }
    }

    private fun displayInitialData() {
        tv_explanation.appendText("The FlatMap operator transform the items emitted by an " +
                "Observable into Observables, then flatten the emissions from those into a single Observable")
        tv_explanation.appendText("Initial items are: 1, 2, 3, 4, 5")
        tv_explanation.appendText("Output: Each emitted item is added by itself and multiplied by itself and emitted as output..")
        tv_explanation.appendText("Example: Emitted value (1) => (1+1 = 2) => (2*2 = 4) => 4")
    }
}

