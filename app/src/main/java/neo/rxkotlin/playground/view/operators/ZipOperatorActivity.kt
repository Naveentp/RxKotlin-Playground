package neo.rxkotlin.playground.view.operators

import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*
import neo.rxkotlin.playground.BaseActivity
import neo.rxkotlin.playground.R
import neo.rxkotlin.playground.model.User
import neo.rxkotlin.playground.utility.Utils
import neo.rxkotlin.playground.utility.appendText

/**
 * @author Naveen T P
 * @since 26/08/18
 */
class ZipOperatorActivity : BaseActivity() {

    private val TAG = ZipOperatorActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        displayInitialData()
        btn.setOnClickListener { doSomething() }
    }

    private fun doSomething() {
        Observable.zip<List<User>, List<User>, List<User>>(getCricketLoversList(), getFootballLoversList(),
                BiFunction<List<User>, List<User>, List<User>> { cricket, football -> Utils().filterUserWhoLovesBothGames(cricket, football) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver())

    }

    private fun getCricketLoversList(): Observable<List<User>> {
        return Observable.create<List<User>> {
            if (!it.isDisposed) {
                it.onNext(Utils().getCricketLovers())
                it.onComplete()
            }
        }
    }

    private fun getFootballLoversList(): Observable<List<User>> {
        return Observable.create<List<User>> {
            if (!it.isDisposed) {
                it.onNext(Utils().getFootballLovers())
                it.onComplete()
            }
        }
    }

    private fun getObserver(): Observer<List<User>> {
        return object : Observer<List<User>> {

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ${d.isDisposed}")
                tv_result.appendText("onSubscribe: Resulting List of lovers who loves both cricket and football")
            }

            override fun onNext(userList: List<User>) {

                tv_result.appendText("onNext: $userList")
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
        tv_explanation.appendText("The Zip operator used to combine two observables and " +
                "strictly pairs emitted items from observables. " +
                "It waits for both (or more) items to arrive then merges them.")
        tv_explanation.appendText("Two different observables:\nCricket Lovers: ${Utils().getCricketLovers()}")
        tv_explanation.appendText("Football Lovers: ${Utils().getFootballLovers()}")
    }
}