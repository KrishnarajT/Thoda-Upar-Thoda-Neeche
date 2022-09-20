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

private const val timeMaxValue = 15


class IntermediateGameActivity : AppCompatActivity() {
    private lateinit var tvUserPercentage1_inter : TextView
    private lateinit var tvUserPercentage2_inter: TextView
    private lateinit var tvGoalPercentage1_inter : TextView
    private lateinit var tvGoalPercentage2_inter: TextView
    private lateinit var tvTimeLeft: TextView
    private lateinit var seekBar1_inter: SeekBar
    private lateinit var seekBar2_inter: SeekBar

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intermediate_game)
        Log.i(TAG, "Started easy game $difficulty")

        tvUserPercentage1_inter = findViewById(R.id.tvUserPercentage1_inter)
//        tvUserPercentage2_inter = findViewById(R.id.tvUserPercentage2_inter)

        tvGoalPercentage1_inter = findViewById(R.id.tvGoalPercentage1_inter)
//        tvGoalPercentage2_inter = findViewById(R.id.tvGoalPercentage2_inter)

        tvTimeLeft = findViewById(R.id.tvTimeLeft)
        seekBar1_inter = findViewById(R.id.seekBar1_inter)
//        seekBar2_inter = findViewById(R.id.seekBar2_inter)


        assignRandomGoalPercentage()

        tvUserPercentage1_inter.text = "$initialSeekbar_1Percentage %"
//        tvUserPercentage2_inter.text = "$initialSeekbar_1Percentage %"

        seekBar1_inter.max = 1000
        seekBar1_inter.progress = initialSeekbar_1Percentage
//
//        seekBar2_inter.max = 1000
//        seekBar2_inter.progress = initialSeekbar_1Percentage


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

        @SuppressLint("LongLogTag")
        fun checkValue() {
            if(tvUserPercentage1_inter.text == tvGoalPercentage1_inter.text){
                Log.i(TAG, "correct")
                gameResult = true
                timer.cancel()
                startActivity(gameOverIntent)
            }

        }

        seekBar1_inter.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                tvUserPercentage1_inter.text = "${p1.toDouble()/10} %"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                checkValue()
            }
        })
//        seekBar2_inter.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
//            @SuppressLint("SetTextI18n")
//            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
//                Log.i(TAG, "onProgressChanged $p1")
//                val user2val  = p1/10
//                tvUserPercentage2_inter.text = "${user2val.toDouble()} %"
//            }
//
//            override fun onStartTrackingTouch(p0: SeekBar?) {
//            }
//
//            override fun onStopTrackingTouch(p0: SeekBar?) {
//                checkValue()
//            }
//        })


    }

    @SuppressLint("SetTextI18n")
    private fun assignRandomGoalPercentage() {
        val rounded1: Double = String.format("%.1f", Random.nextDouble(0.0, 100.0)).toDouble()
        tvGoalPercentage1_inter.text = "$rounded1 %"
//        String.format("%.1f %", Random.nextDouble(0.0, 100.0))
//        tvGoalPercentage2_inter.text = "${Random.nextInt(0, 100)} %"
    }


}