package com.ajayvamsee.rxjava.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ajayvamsee.rxjava.R
import com.ajayvamsee.rxjava.ui.operators.search.SearchActivity

class SelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
    }

    fun startOperatorsActivity(view: View){
        startActivity(Intent(this@SelectionActivity,OperatorsActivity::class.java))
    }

    fun startSearchActivity(view: View){
        startActivity(Intent(this@SelectionActivity,SearchActivity::class.java))
    }
}