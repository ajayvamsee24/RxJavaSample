package com.ajayvamsee.rxjava.ui.operators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.ajayvamsee.rxjava.R
import com.ajayvamsee.rxjava.utils.AppConstant
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.just
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class DelayExampleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DelayExampleActivity"
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

        getObservable()
            .delay(2,TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getObserver())

    }


    private fun getObservable(): Observable<String>{
        return Observable.just("AMIT")
    }

    private fun getObserver(): Observer<String> {
       return object : Observer<String>{
           override fun onSubscribe(d: Disposable) {

           }

           override fun onNext(t: String) {
               textView.append(" onNext : value : $t")
               textView.append(AppConstant.LINE_SEPERATOR)
           }

           override fun onError(e: Throwable) {
               textView.append(" onError : " + e.message)
               textView.append(AppConstant.LINE_SEPERATOR)
           }

           override fun onComplete() {
               textView.append(" onComplete")
               textView.append(AppConstant.LINE_SEPERATOR)
           }

       }
    }
}