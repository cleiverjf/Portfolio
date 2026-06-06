package com.viacil.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCamera: MaterialButton = findViewById(R.id.btnCamera)
        val btnInterpreter: MaterialButton = findViewById(R.id.btnInterpreter)

        btnCamera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        btnInterpreter.setOnClickListener {
            val intent = Intent(this, InterpreterActivity::class.java)
            startActivity(intent)
        }
    }
}