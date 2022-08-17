package com.ajayvamsee.rxjava.ui.operators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.ajayvamsee.rxjava.R
import com.ajayvamsee.rxjava.model.User
import com.ajayvamsee.rxjava.utils.AppConstant
import com.ajayvamsee.rxjava.utils.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers

class ZipExampleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ZipExampleActivity"
    }

    private lateinit var btn: Button
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        btn = findViewById(R.id.btn)
        textView = findViewById(R.id.textView)

        btn.setOnClickListener {
            doSomeWork()
        }
    }

    private fun doSomeWork() {
        Observable.zip(getCricketFansObservable(),getFootballFansObservable(),
            BiFunction { cricketFans, footballFans ->
                return@BiFunction Utils.filterUserWhoLovesBoth(cricketFans,footballFans)
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getObserver())
    }

    private fun getCricketFansObservable(): Observable<List<User>>{
        return Observable.create(ObservableOnSubscribe<List<User>>{ e->
            if(!e.isDisposed){
                e.onNext(Utils.getUserListWhoLovesCricket())
                e.onComplete()
            }
        }).subscribeOn(Schedulers.io())
    }

    private fun getFootballFansObservable() : Observable<List<User>>{
        return Observable.create(ObservableOnSubscribe<List<User>>{ e->
            if(!e.isDisposed){
                e.onNext(Utils.getUserListWhoLovesFootball())
                e.onComplete()
            }
        }).subscribeOn(Schedulers.io())
    }

    private fun getObserver(): Observer<List<User>>{
        return object : Observer<List<User>>{
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed)
            }

            override fun onNext(t: List<User>) {
                textView.append(" onNext")
                textView.append(AppConstant.LINE_SEPERATOR)
                for (user in t) {
                    textView.append(" firstname : ${user.firstName}")
                    textView.append(AppConstant.LINE_SEPERATOR)
                }
                Log.d(TAG, " onNext : " + t.size)
            }

            override fun onError(e: Throwable) {
                textView.append(" onError : " + e.message)
                textView.append(AppConstant.LINE_SEPERATOR)
                Log.d(TAG, " onError : " + e.message)
            }

            override fun onComplete() {
                textView.append(" onComplete")
                textView.append(AppConstant.LINE_SEPERATOR)
                Log.d(TAG, " onComplete")
            }

        }
    }
}