package com.ajayvamsee.rxjava.ui.operators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.ajayvamsee.rxjava.R
import com.ajayvamsee.rxjava.model.ApiUser
import com.ajayvamsee.rxjava.model.User
import com.ajayvamsee.rxjava.utils.AppConstant
import com.ajayvamsee.rxjava.utils.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MapExampleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MapExampleActivity"
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
        getObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { apiUsers ->
                return@map Utils.convertApiUserListToUserList(apiUsers)
            }
            .subscribe(getObserver())

    }

    private fun getObservable(): Observable<List<ApiUser>>{
        return Observable.create { e ->
            if(!e.isDisposed){
                e.onNext(Utils.getApiUserList())
                e.onComplete()
            }
        }
    }

    private fun getObserver() : Observer<List<User>>{
        return object : Observer<List<User>>{
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed)
            }

            override fun onNext(t: List<User>) {
                textView.append(" onNext")
                textView.append(AppConstant.LINE_SEPERATOR)

                for (user in t){
                    textView.append("FirstName : ${user.firstName}")
                    textView.append(AppConstant.LINE_SEPERATOR)
                }
            }

            override fun onError(e: Throwable) {
                textView.append(" onError : " + e.message)
                textView.append(AppConstant.LINE_SEPERATOR)
                Log.d(TAG, " onError : " + e.message)
            }

            override fun onComplete() {
                textView.append(" onComplete")
                textView.append(AppConstant.LINE_SEPERATOR)
            }

        }
    }
}