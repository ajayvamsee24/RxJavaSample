package com.ajayvamsee.rxjava.ui.operators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.ajayvamsee.rxjava.R
import com.ajayvamsee.rxjava.utils.AppConstant
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class FilterExampleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "FilterExampleActivity"
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

    private fun doSomeWork(){
        Observable.just(1,2,3,4,5,6)
            .filter{ value ->
                return@filter value %2 == 0
            }
            .subscribe(getObserver())
    }

    private fun getObserver():Observer<Int>{
        return object : Observer<Int>{
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Int) {
                textView.append(" onNext : ")
                textView.append(AppConstant.LINE_SEPERATOR)
                textView.append(" value : $t")
                textView.append(AppConstant.LINE_SEPERATOR)
                Log.d(TAG, " onNext ")
                Log.d(TAG, " value : $t")
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