package com.example.zastosowaniesortowan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.bubble).setOnClickListener {
            val intent = Intent(this,BabelkoweActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.fast).setOnClickListener {
            val intent = Intent(this,SzybkieActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.scalanie).setOnClickListener {
            val intent = Intent(this,ScalanieActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.wstawianie).setOnClickListener {
            val intent = Intent(this,WstawianieActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.heapsort).setOnClickListener {
            val intent = Intent(this,HeapsortActivity::class.java)
            startActivity(intent)
        }
    }
}