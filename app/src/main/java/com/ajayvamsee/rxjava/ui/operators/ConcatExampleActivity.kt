package com.ajayvamsee.rxjava.ui.operators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.ajayvamsee.rxjava.R
import com.ajayvamsee.rxjava.utils.AppConstant
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class ConcatExampleActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "ConcatExampleActivity"
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
       val observableA = Observable.fromArray("A1","A2","A3","A4")
       val observableB = Observable.fromArray("B1","B2","B3","B4")

        Observable.concat(observableA,observableB)
            .subscribe(getObserver())
    }

    private fun getObserver(): Observer<String>{
        return object : Observer<String>{
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: String) {
                textView.append(" onNext : value : $t")
                textView.append(AppConstant.LINE_SEPERATOR)
            }

            override fun onError(e: Throwable) {
                textView.append(" onError : "+ e.message)
                textView.append(AppConstant.LINE_SEPERATOR)
            }

            override fun onComplete() {
                textView.append(" onCompleted")
                textView.append(AppConstant.LINE_SEPERATOR)
            }

        }
    }
}