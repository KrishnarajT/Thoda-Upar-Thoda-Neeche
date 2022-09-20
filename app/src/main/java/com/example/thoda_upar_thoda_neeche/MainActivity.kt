package com.example.thoda_upar_thoda_neeche

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View

const val EXTRA_MESSAGE = "com.example.thoda_upar_thoda_neeche.MESSAGE"
public var difficulty = ""
public var gameResult: Boolean = false


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startEasyGame(view: View) {
        val easyIntent = Intent(this, EasyGameActivity::class.java).apply {
            difficulty = "Easy"
            putExtra(EXTRA_MESSAGE, difficulty)
        }
        startActivity(easyIntent)
    }
    fun startIntermediateGame(view: View) {
        val interdiateIntent = Intent(this, IntermediateGameActivity::class.java).apply {
            difficulty = "Easy"
            putExtra(EXTRA_MESSAGE, difficulty)
        }
        startActivity(interdiateIntent)
    }

}