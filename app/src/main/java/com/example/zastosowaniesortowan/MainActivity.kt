package com.example.zastosowaniesortowan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val przyciski = listOf<Button>(
            findViewById(R.id.bubble),
            findViewById(R.id.fast),
            findViewById(R.id.scalanie),
            findViewById(R.id.wstawianie),
            findViewById(R.id.heapsort)
        )

        przyciski[0].setOnClickListener {
            val intent = Intent(this,BabelkoweActivity::class.java)
            startActivity(intent)
        }
        przyciski[1].setOnClickListener {
            val intent = Intent(this,SzybkieActivity::class.java)
            startActivity(intent)
        }
        przyciski[2].setOnClickListener {
            val intent = Intent(this,ScalanieActivity::class.java)
            startActivity(intent)
        }
        przyciski[3].setOnClickListener {
            val intent = Intent(this,WstawianieActivity::class.java)
            startActivity(intent)
        }
        przyciski[4].setOnClickListener {
            val intent = Intent(this,BabelkoweActivity::class.java)
            startActivity(intent)
        }
    }
}