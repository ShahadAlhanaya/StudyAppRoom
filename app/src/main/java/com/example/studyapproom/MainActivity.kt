package com.example.studyapproom
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var kotlinButton: Button
    lateinit var androidButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kotlinButton = findViewById(R.id.kotlin_button)
        androidButton = findViewById(R.id.android_button)

        kotlinButton.setOnClickListener {
            goToKotlin()
        }
        androidButton.setOnClickListener {
            goToAndroid()
        }

    }

    private fun goToKotlin() {
        val intent = Intent(this, KotlinActivity::class.java)
        startActivity(intent)
    }

    private fun goToAndroid() {
        val intent = Intent(this, AndroidActivity::class.java)
        startActivity(intent)
    }
}