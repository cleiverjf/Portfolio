package com.viacil.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viacil.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCamera.setOnClickListener {
            startActivityWithTransition(CameraActivity::class.java, R.anim.rotate_scale, R.anim.explode_fly)
        }

        binding.btnInterpreter.setOnClickListener {
            startActivityWithTransition(InterpreterActivity::class.java, R.anim.fade_in_scale, R.anim.slide_up_fade)
        }

        binding.btnTranslation.setOnClickListener {
            startActivityWithTransition(TranslationActivity::class.java, R.anim.scale_up, R.anim.slide_in_right)
        }

        binding.btnSettings.setOnClickListener {
            startActivityWithTransition(SettingsActivity::class.java, R.anim.lens_focus, R.anim.fade_in_scale)
        }
    }

    private fun startActivityWithTransition(activityClass: Class<*>, enterAnim: Int, exitAnim: Int) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
        overridePendingTransition(enterAnim, exitAnim)
    }
}