package com.example.app_record

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.app_record.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val resultIntent = Intent()

            val sys = binding.etSys.text.toString().toIntOrNull() ?: 0
            val dia = binding.etDia.text.toString().toIntOrNull() ?: 0
            val hr = binding.etHr.text.toString().toIntOrNull() ?: 0
            resultIntent.putExtra("sys", sys)
            resultIntent.putExtra("dia", dia)
            resultIntent.putExtra("hr", hr)

            setResult(Activity.RESULT_OK, resultIntent)
            val message = "New Record Added: Sys: $sys, Dia: $dia, HR: $hr"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
