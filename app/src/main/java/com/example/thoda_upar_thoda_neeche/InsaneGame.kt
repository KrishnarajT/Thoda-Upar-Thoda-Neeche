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


private const val TAG = "InsaneGameActivity"

private const val initialSeekbar_1Percentage = 0

private const val timeMaxValue = 200

class InsaneGame : AppCompatActivity() {


    private lateinit var timer: CountDownTimer
    private lateinit var tvTimeLeftLabel_insane: TextView
    private lateinit var tvTimeLeft_insane: TextView

    private lateinit var tvGoalPercentage1_insane: TextView
    private lateinit var tvGoalPercentage2_insane: TextView
    private lateinit var tvGoalPercentage3_insane: TextView
    private lateinit var tvGoalPercentage4_insane: TextView
    private lateinit var tvGoalPercentage5_insane: TextView

    private lateinit var tvUserPercentage1_insane: TextView
    private lateinit var tvUserPercentage2_insane: TextView
    private lateinit var tvUserPercentage3_insane: TextView
    private lateinit var tvUserPercentage4_insane: TextView
    private lateinit var tvUserPercentage5_insane: TextView

    private lateinit var seekBar1_insane: SeekBar
    private lateinit var seekBar2_insane: SeekBar
    private lateinit var seekBar3_insane: SeekBar
    private lateinit var seekBar4_insane: SeekBar
    private lateinit var seekBar5_insane: SeekBar


    override fun onBackPressed() {
        //this is only needed if you have specific things
        //that you want to do when the user presses the back button.
        /* your specific things...*/
//        super.onBackPressed()
        timer.cancel()
        this.finish()
        val mainActivityIntent = Intent(this, MainActivity::class.java).apply {}
        startActivity(mainActivityIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insane_game)

        tvTimeLeftLabel_insane = findViewById(R.id.tvTimeLeftLabel_insane)
        tvTimeLeft_insane = findViewById(R.id.tvTimeLeft_insane)

        tvGoalPercentage1_insane = findViewById(R.id.tvGoalPercentage1_insane)
        tvGoalPercentage2_insane = findViewById(R.id.tvGoalPercentage2_insane)
        tvGoalPercentage3_insane = findViewById(R.id.tvGoalPercentage3_insane)
        tvGoalPercentage4_insane = findViewById(R.id.tvGoalPercentage4_insane)
        tvGoalPercentage5_insane = findViewById(R.id.tvGoalPercentage5_insane)

        tvUserPercentage1_insane = findViewById(R.id.tvUserPercentage1_insane)
        tvUserPercentage2_insane = findViewById(R.id.tvUserPercentage2_insane)
        tvUserPercentage3_insane = findViewById(R.id.tvUserPercentage3_insane)
        tvUserPercentage4_insane = findViewById(R.id.tvUserPercentage4_insane)
        tvUserPercentage5_insane = findViewById(R.id.tvUserPercentage5_insane)

        seekBar1_insane = findViewById(R.id.seekBar1_insane)
        seekBar2_insane = findViewById(R.id.seekBar2_insane)
        seekBar3_insane = findViewById(R.id.seekBar3_insane)
        seekBar4_insane = findViewById(R.id.seekBar4_insane)
        seekBar5_insane = findViewById(R.id.seekBar5_insane)

        seekBar1_insane.max = 400
        seekBar2_insane.max = 400
        seekBar3_insane.max = 400
        seekBar4_insane.max = 700
        seekBar5_insane.max = 700

        tvUserPercentage1_insane.text = "0.0 %"
        tvUserPercentage2_insane.text = "0.0 %"
        tvUserPercentage3_insane.text = "0.0 %"
        tvUserPercentage4_insane.text = "0.0 %"
        tvUserPercentage5_insane.text = "0.0 %"


        assignRandomValues()

        val gameOverIntent = Intent(this, GameOver::class.java).apply {}

        timer = object : CountDownTimer((timeMaxValue * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimeLeft_insane.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                gameResult = false
                this.cancel()
                startActivity(gameOverIntent)
            }
        }
        timer.start()


        fun checkValue() {
            if (tvUserPercentage1_insane.text == tvGoalPercentage1_insane.text
                && tvUserPercentage2_insane.text == tvGoalPercentage2_insane.text
                && tvUserPercentage3_insane.text == tvGoalPercentage3_insane.text
                && tvUserPercentage4_insane.text == tvGoalPercentage4_insane.text
                && tvUserPercentage5_insane.text == tvGoalPercentage5_insane.text
            ) {
                Log.i(TAG, "correct")
                gameResult = true
                timer.cancel()
                startActivity(gameOverIntent)
            }

        }


        seekBar1_insane.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                tvUserPercentage1_insane.text = "${p1.toDouble() / 10} %"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                checkValue()
            }
        })

        seekBar2_insane.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                tvUserPercentage2_insane.text = "${p1.toDouble() / 10} %"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                checkValue()
            }
        })

        seekBar3_insane.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                tvUserPercentage3_insane.text = "${p1.toDouble() / 10} %"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                checkValue()
            }
        })

        seekBar4_insane.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged s4 $p1")
                tvUserPercentage4_insane.text = "${p1.toDouble() / 10} %"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                checkValue()
            }
        })

        seekBar5_insane.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged s5 $p1")
                tvUserPercentage5_insane.text = "${p1.toDouble() / 10} %"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                checkValue()
            }
        })
    }


    @SuppressLint("SetTextI18n")
    private fun assignRandomValues() {
        tvGoalPercentage1_insane.text =
            (Random.nextInt(0, seekBar1_insane.max).toDouble() / 10.0).toString() + " %"
        tvGoalPercentage2_insane.text =
            (Random.nextInt(0, seekBar2_insane.max).toDouble() / 10.0).toString() + " %"
        tvGoalPercentage3_insane.text =
            (Random.nextInt(0, seekBar3_insane.max).toDouble() / 10.0).toString() + " %"
        tvGoalPercentage4_insane.text =
            (Random.nextInt(0, seekBar4_insane.max).toDouble() / 10.0).toString() + " %"
        tvGoalPercentage5_insane.text =
            (Random.nextInt(0, seekBar5_insane.max).toDouble() / 10.0).toString() + " %"
    }
}