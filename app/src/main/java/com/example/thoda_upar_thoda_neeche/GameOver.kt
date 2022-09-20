package com.example.thoda_upar_thoda_neeche

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import android.provider.AlarmClock
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text

class GameOver : AppCompatActivity() {
    private lateinit var tvGameOver: TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)


        tvGameOver = findViewById(R.id.tvGameOver)
        if (gameResult) {
            tvGameOver.text = "Congrats! \n Well Done!"
        } else {
            tvGameOver.text = "Sad...\n You lost"
        }
    }

    fun callMainActivity(view : View)
    {
        val mainActivityIntent = Intent(this, MainActivity::class.java).apply {}
        startActivity(mainActivityIntent)
    }

}