package com.ajayvamsee.rxjava.utils

import android.widget.SearchView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

/**
 * Created by Ajay Vamsee on 8/12/2022.
 * Time : 20:23 HRS
 */
fun SearchView.getQueryTextChangeObservable(): Observable<String> {

    val subject = PublishSubject.create<String>()

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            subject.onComplete()
            return true
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            subject.onNext(p0)
            return true
        }

    })
    return subject

}