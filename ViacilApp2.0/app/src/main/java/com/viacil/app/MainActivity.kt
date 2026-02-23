package com.viacil.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cameraButton = findViewById<Button>(R.id.btnCamera)
        val interpreterButton = findViewById<Button>(R.id.btnInterpreter)

        cameraButton.setOnClickListener {
            // TODO: Lanzar actividad de cámara
        }

        interpreterButton.setOnClickListener {
            // TODO: Lanzar actividad de intérprete
        }
    }
}
