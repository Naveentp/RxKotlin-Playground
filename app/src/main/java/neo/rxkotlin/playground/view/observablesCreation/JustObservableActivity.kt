package neo.rxkotlin.playground.view.observablesCreation

import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*
import neo.rxkotlin.playground.BaseActivity
import neo.rxkotlin.playground.R
import neo.rxkotlin.playground.utility.appendText


/**
* @author Naveen T P
* @since 25/08/18
*/

class JustObservableActivity : BaseActivity() {

    private val TAG = JustObservableActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        displayInitialData()
        btn.setOnClickListener { doSomething() }
    }

    private fun doSomething() {
        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver())
    }

    private fun getObservable(): Observable<String> = Observable.just("Cricket", "Football")

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ${d.isDisposed}")
                tv_result.appendText("onSubscribe: Emitting items..")
            }

            override fun onNext(value: String) {
                tv_result.appendText("onNext : value : $value")
            }

            override fun onComplete() {
                tv_result.appendText("onComplete")
            }

            override fun onError(e: Throwable) {
                tv_result.appendText("onError: ${e.message}")
                Log.d(TAG, "onError: ${e.message}")
            }
        }
    }

    private fun displayInitialData() {
        tv_explanation.appendText("The Just operator converts an item into an Observable that emits that item.")
        tv_explanation.appendText("Items to be emitted are: Cricket, Football")
    }
}