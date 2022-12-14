package com.ajayvamsee.rxjava.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ajayvamsee.rxjava.R
import com.ajayvamsee.rxjava.ui.operators.*

class OperatorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operators)
    }

    fun startSimpleActivity(view: View){
        startActivity(Intent(this@OperatorsActivity,SimpleExampleActivity::class.java))
    }

    fun startMapActivity(view: View){
        startActivity(Intent(this@OperatorsActivity,SimpleExampleActivity::class.java))
    }

    fun startZipActivity(view: View){
        startActivity(Intent(this@OperatorsActivity,ZipExampleActivity::class.java))
    }

    fun startTimerActivity(view: View){
        startActivity(Intent(this@OperatorsActivity,TimerExampleActivity::class.java))
    }

    fun startFilterActivity(view: View){
        startActivity(Intent(this@OperatorsActivity,FilterExampleActivity::class.java))
    }

    fun startConcatActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, ConcatExampleActivity::class.java))
    }

    fun startMergeActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, MergeExampleActivity::class.java))
    }

    fun startDelayActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, DelayExampleActivity::class.java))
    }


}