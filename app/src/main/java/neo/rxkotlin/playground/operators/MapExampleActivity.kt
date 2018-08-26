package neo.rxkotlin.playground.operators

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
import neo.rxkotlin.playground.model.ApiUser
import neo.rxkotlin.playground.model.User
import neo.rxkotlin.playground.utility.Utils
import neo.rxkotlin.playground.utility.appendText

/**
 * @author Naveen T P
 * @since 25/08/18
 */

class MapExampleActivity : BaseActivity() {

    val TAG = MapExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        btn.setOnClickListener { doSomething() }
    }

    private fun doSomething() {
        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { apiUser -> Utils().convertApiUserToUser(apiUser) }
                .subscribe(getObserver())
    }

    private fun getObservable(): Observable<List<ApiUser>> {
        return Observable.create<List<ApiUser>> { emitter ->
            if (!emitter.isDisposed) {
                emitter.onNext(Utils().getApiUsersList())
                emitter.onComplete()
            }
        }
    }

    private fun getObserver(): Observer<List<User>> {
        return object : Observer<List<User>> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ${d.isDisposed}")
            }

            override fun onNext(userList: List<User>) {
                tv_result.appendText("onNext")
                userList.forEach {
                    tv_result.appendText("User Name: ${it.name}")
                }
            }

            override fun onComplete() {
                tv_result.appendText("onComplete")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError: ${e.message}")
            }

        }
    }
}