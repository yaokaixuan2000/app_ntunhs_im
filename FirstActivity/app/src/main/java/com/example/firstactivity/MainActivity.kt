package com.example.firstactivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnChangeActivity = findViewById<Button>(R.id.btnChangeActivity)
        val btnChangeActivity2 = findViewById<Button>(R.id.btnChangeActivity2)
        val btnOpenBrowser = findViewById<Button>(R.id.btnOpenBrowser)
        btnChangeActivity.setOnClickListener{
            var bundle = Bundle()

            var seconInent = Intent(this,GuessNumberActivity::class.java)
            seconInent.putExtra("key",bundle)
            startActivity(seconInent)
        }
        btnChangeActivity2.setOnClickListener{
            var bundle = Bundle()

            var seconInent = Intent(this,PaperScissorsStone::class.java)
            seconInent.putExtra("key",bundle)
            startActivity(seconInent)
        }

        btnOpenBrowser.setOnClickListener{
            var seconIntent = Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com"))
            startActivity(seconIntent)
        }
    }
}