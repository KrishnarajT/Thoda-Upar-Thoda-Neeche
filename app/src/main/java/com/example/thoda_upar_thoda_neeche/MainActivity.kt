package com.example.thoda_upar_thoda_neeche

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View

const val EXTRA_MESSAGE = "com.example.thoda_upar_thoda_neeche.MESSAGE"
var difficulty = ""
var gameResult: Boolean = false
var musicPlaying: Boolean = true
var sfx: Boolean = true
private const val TAG = "MainActivity"


lateinit var BgMusic: MediaPlayer
lateinit var clickSound: MediaPlayer
var runOnce: Boolean = true

fun playBgMusic() {
    BgMusic?.setOnPreparedListener {
        if (!BgMusic.isPlaying()) {
            BgMusic?.start()
            musicPlaying = true
            Log.i(TAG, "ma, on")
        }
    }
//    BgMusic.setLooping(true)
    BgMusic?.setOnCompletionListener {
        BgMusic?.start()
    }
}

fun pauseBgMusic() {
    if (BgMusic.isPlaying()) {
        BgMusic.pause();
        musicPlaying = false
        Log.i(TAG, "ma, off")

    }
}

fun resumeBgMusic() {
    if (!BgMusic.isPlaying()) {
        BgMusic?.start()
        musicPlaying = true
        Log.i(TAG, "ma, on")
    }
}


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BgMusic = MediaPlayer.create(this, R.raw.bgm)
        if (runOnce) {
            playBgMusic()
            runOnce = false
        }
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
            difficulty = "Intermediate"
            putExtra(EXTRA_MESSAGE, difficulty)
        }
        startActivity(interdiateIntent)
    }

    fun startHardGame(view: View) {
        val hardIntent = Intent(this, HardGame::class.java).apply {
            difficulty = "Hard"
            putExtra(EXTRA_MESSAGE, difficulty)
        }
        startActivity(hardIntent)
    }

    fun startInsaneGame(view: View) {
        val insaneIntent = Intent(this, InsaneGame::class.java).apply {
            difficulty = "Insane"
            putExtra(EXTRA_MESSAGE, difficulty)
        }
        startActivity(insaneIntent)
    }

    fun startSettings(view: View) {
        val settingsIntent = Intent(this, Settings::class.java).apply {
            putExtra(EXTRA_MESSAGE, difficulty)
        }
        startActivity(settingsIntent)
    }

}