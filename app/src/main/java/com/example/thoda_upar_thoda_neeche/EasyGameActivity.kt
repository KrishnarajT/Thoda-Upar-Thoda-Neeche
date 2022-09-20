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

private const val TAG = "GameActivity"

private const val initialSeekbar_1Percentage = 50

private const val timeMaxValue = 15


class EasyGameActivity : AppCompatActivity() {
    private lateinit var tvGoalPercentage : TextView
    private lateinit var tvUserPercentage: TextView
    private lateinit var tvTimeLeft: TextView
    private lateinit var seekBar1: SeekBar

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_easy)
        Log.i(TAG, "Started easy game $difficulty")

        tvUserPercentage = findViewById(R.id.tvUserPercentage_easy)
        tvGoalPercentage = findViewById(R.id.tvGoalPercentage_easy)
        tvTimeLeft = findViewById(R.id.tvTimeLeft)
        seekBar1 = findViewById(R.id.seekBar_Easy)

        assignRandomGoalPercentage()

        tvUserPercentage.text = "$initialSeekbar_1Percentage %"
        seekBar1.max = 100
        seekBar1.progress = initialSeekbar_1Percentage




        val gameOverIntent = Intent(this, GameOver::class.java).apply {}

        val timer = object: CountDownTimer((timeMaxValue * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimeLeft.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                gameResult = false
                startActivity(gameOverIntent)
            }
        }
        timer.start()

        fun checkValue() {
            if(tvUserPercentage.text == tvGoalPercentage.text) {
                Log.i(TAG, "correct")
                gameResult = true
                timer.cancel()
                startActivity(gameOverIntent)
            }
        }

        seekBar1.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                tvUserPercentage.text = "$p1 %"
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
        tvGoalPercentage.text = "${Random.nextInt(0, 100)} %"
    }


}