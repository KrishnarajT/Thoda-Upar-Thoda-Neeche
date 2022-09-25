package com.example.thoda_upar_thoda_neeche

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import kotlin.random.Random

private const val TAG = "InterGameActivity"

private const val initialSeekbar_1Percentage = 0

private const val timeMaxValue = 16


class IntermediateGameActivity : AppCompatActivity() {
    private lateinit var tvUserPercentage1_inter: TextView
    private lateinit var tvGoalPercentage1_inter: TextView
    private lateinit var tvTimeLeft: TextView
    private lateinit var seekBar1_inter: SeekBar


    private lateinit var timer: CountDownTimer

    override fun onBackPressed() {
//        super.onBackPressed()
        timer.cancel()
        this.finish()
        val mainActivityIntent = Intent(this, MainActivity::class.java).apply {}
        startActivity(mainActivityIntent)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intermediate_game)
        Log.i(TAG, "Started easy game $difficulty")

        tvUserPercentage1_inter = findViewById(R.id.tvUserPercentage1_inter)
        tvGoalPercentage1_inter = findViewById(R.id.tvGoalPercentage1_inter)

        tvTimeLeft = findViewById(R.id.tvTimeLeft)
        seekBar1_inter = findViewById(R.id.seekBar1_inter)


        assignRandomGoalPercentage()

        tvUserPercentage1_inter.text = "$initialSeekbar_1Percentage %"

        seekBar1_inter.max = 1000
        seekBar1_inter.progress = initialSeekbar_1Percentage


        val gameOverIntent = Intent(this, GameOver::class.java).apply {}

        timer = object : CountDownTimer((timeMaxValue * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimeLeft.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                gameResult = false
                this.cancel()
                startActivity(gameOverIntent)
            }
        }
        timer.start()

        @SuppressLint("LongLogTag")
        fun checkValue() {
            if (tvUserPercentage1_inter.text == tvGoalPercentage1_inter.text) {
                Log.i(TAG, "correct")
                gameResult = true
                timer.cancel()
                startActivity(gameOverIntent)
            }

        }

        seekBar1_inter.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                tvUserPercentage1_inter.text = "${p1.toDouble() / 10} %"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                checkValue()
            }
        })


    }

    @SuppressLint("SetTextI18n")
    private fun assignRandomGoalPercentage() {
        val rounded1: Double = String.format("%.1f", Random.nextDouble(0.0, 100.0)).toDouble()
        tvGoalPercentage1_inter.text = "$rounded1 %"
    }


}