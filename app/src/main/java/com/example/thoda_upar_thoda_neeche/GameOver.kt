package com.example.thoda_upar_thoda_neeche

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameOver : AppCompatActivity() {
    private lateinit var tvGameOver: TextView

    override fun onBackPressed() {
        //this is only needed if you have specific things
        //that you want to do when the user presses the back button.
        /* your specific things...*/
//        super.onBackPressed()
        this.finish()
        val mainActivityIntent = Intent(this, MainActivity::class.java).apply {}
        startActivity(mainActivityIntent)
    }

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

    fun callMainActivity(view: View) {
        val mainActivityIntent = Intent(this, MainActivity::class.java).apply {}
        startActivity(mainActivityIntent)
    }

}