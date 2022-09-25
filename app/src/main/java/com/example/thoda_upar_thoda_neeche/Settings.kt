package com.example.thoda_upar_thoda_neeche

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity


private const val TAG = "SettingsActivity"


class Settings : AppCompatActivity() {
    private lateinit var swMusicSwitch: Switch
    private lateinit var swSFX: Switch
    override fun onBackPressed() {
        //this is only needed if you have specific things
        //that you want to do when the user presses the back button.
        /* your specific things...*/
//        super.onBackPressed()
        this.finish()
        val mainActivityIntent = Intent(this, MainActivity::class.java).apply {}
        startActivity(mainActivityIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)



        swMusicSwitch = findViewById(R.id.swMusicSwitch)
        swSFX = findViewById(R.id.swSFX)
        if (musicPlaying) {
            swMusicSwitch.setChecked(true)
        }
        swSFX.setChecked(true)

        swMusicSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
                Log.i(TAG, "sa on")
                resumeBgMusic()
            } else {
                Log.i(TAG, "sa off")
                pauseBgMusic()
                // The toggle is disabled
            }
        })
        swSFX.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
                Log.i(TAG, "on")
                sfx = true
            } else {
                Log.i(TAG, "off")
                sfx = false
                // The toggle is disabled
            }
        })
    }

}