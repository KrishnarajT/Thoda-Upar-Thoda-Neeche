package com.example.thoda_upar_thoda_neeche

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

private const val TAG = "HardGameActivity"

private var initialSeekbar_1Percentage = 0
private var initialSeekbar_2Percentage = 0
private var initialSeekbar_3Percentage = 0

private var finalSeekbar_1Percentage = 0
private var finalSeekbar_2Percentage = 0
private var finalSeekbar_3Percentage = 0
private const val timeMaxValue = 16


class HardGame : AppCompatActivity() {


    private lateinit var tvUserPercentage1_hard: TextView
    private lateinit var tvGoalPercentage1_hard: TextView
    private lateinit var tvUserPercentage2_hard: TextView
    private lateinit var tvGoalPercentage2_hard: TextView
    private lateinit var tvUserPercentage3_hard: TextView
    private lateinit var tvGoalPercentage3_hard: TextView

    private lateinit var tvTimeLeft_hard: TextView
    private lateinit var seekBar1_hard: SeekBar
    private lateinit var seekBar2_hard: SeekBar
    private lateinit var seekBar3_hard: SeekBar


    private lateinit var timer: CountDownTimer

    override fun onBackPressed() {
//        super.onBackPressed()
        timer.cancel()
        this.finish()
        val mainActivityIntent = Intent(this, MainActivity::class.java).apply {}
        startActivity(mainActivityIntent)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_game)
        Log.i(TAG, "Started Hard game $difficulty")

        // Assigning Variables
        tvUserPercentage1_hard = findViewById(R.id.tvUserPercentage1_hard)
        tvUserPercentage2_hard = findViewById(R.id.tvUserPercentage2_hard)
        tvUserPercentage3_hard = findViewById(R.id.tvUserPercentage3_hard)

        tvGoalPercentage1_hard = findViewById(R.id.tvGoalPercentage1_hard)
        tvGoalPercentage2_hard = findViewById(R.id.tvGoalPercentage2_hard)
        tvGoalPercentage3_hard = findViewById(R.id.tvGoalPercentage3_hard)

        seekBar1_hard = findViewById(R.id.seekBar1_Hard)
        seekBar2_hard = findViewById(R.id.seekBar2_Hard)
        seekBar3_hard = findViewById(R.id.seekBar3_Hard)

        tvTimeLeft_hard = findViewById(R.id.tvTimeLeft_hard)



        initialSeekbar_1Percentage = Random.nextInt(0, 100)
        initialSeekbar_2Percentage = Random.nextInt(398, 450)
        initialSeekbar_3Percentage = Random.nextInt(1200, 1300)

        finalSeekbar_1Percentage = Random.nextInt(900, 1050)
        finalSeekbar_2Percentage = Random.nextInt(50, 75)
        finalSeekbar_3Percentage = Random.nextInt(600, 700)

        seekBar1_hard.min = initialSeekbar_1Percentage
        seekBar1_hard.max = finalSeekbar_1Percentage
        seekBar2_hard.min = 0
        seekBar2_hard.max = finalSeekbar_2Percentage
        seekBar3_hard.min = 0
        seekBar3_hard.max = finalSeekbar_3Percentage

        assignRandomGoalPercentage()

        val gameOverIntent = Intent(this, GameOver::class.java).apply {}

        timer = object : CountDownTimer((timeMaxValue * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimeLeft_hard.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                gameResult = false
                this.cancel()
                startActivity(gameOverIntent)
            }
        }
        timer.start()

        fun checkValue() {
            if (tvUserPercentage1_hard.text == tvGoalPercentage1_hard.text
                && tvUserPercentage2_hard.text == tvGoalPercentage2_hard.text
                && tvUserPercentage3_hard.text == tvGoalPercentage3_hard.text
            ) {
                Log.i(TAG, "correct")
                gameResult = true
                timer.cancel()
                startActivity(gameOverIntent)
            }

        }

        // Seekbar Function Overloads
        seekBar1_hard.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                tvUserPercentage1_hard.text = "${p1.toDouble() / 10} %"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                checkValue()
            }
        })


        seekBar2_hard.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                tvUserPercentage2_hard.text =
                    "${(p1 + initialSeekbar_2Percentage).toDouble() / 10} %"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                checkValue()
            }
        })


        seekBar3_hard.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                tvUserPercentage3_hard.text =
                    "${(p1 + initialSeekbar_3Percentage).toDouble() / 10} %"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                checkValue()
            }
        })


    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun assignRandomGoalPercentage() {


        tvUserPercentage1_hard.text = "${initialSeekbar_1Percentage.toDouble()} %"
        tvUserPercentage2_hard.text = "${initialSeekbar_2Percentage.toDouble() / 10} %"
        tvUserPercentage3_hard.text = "${initialSeekbar_3Percentage.toDouble() / 10} %"


        val rounded1: String = String.format(
            "%.1f",
            (Random.nextInt(initialSeekbar_1Percentage, finalSeekbar_1Percentage)).toDouble() / 10
        )

        val rounded2: String = String.format(
            "%.1f",
            (Random.nextInt(
                initialSeekbar_2Percentage,
                initialSeekbar_2Percentage + finalSeekbar_2Percentage
            )).toDouble() / 10
        )

        val rounded3: String = String.format(
            "%.1f",
            (Random.nextInt(
                initialSeekbar_3Percentage,
                initialSeekbar_3Percentage + finalSeekbar_3Percentage
            )).toDouble() / 10
        )

        tvGoalPercentage1_hard.text = "$rounded1 %"
        tvGoalPercentage2_hard.text = "$rounded2 %"
        tvGoalPercentage3_hard.text = "$rounded3 %"
    }
}