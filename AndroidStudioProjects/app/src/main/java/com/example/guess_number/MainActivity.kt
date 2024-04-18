package com.example.guess_number

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.guess_number.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    val TAG:String = MainActivity::class.java.simpleName
    private lateinit var handler: Handler
    private lateinit var binding: ActivityMainBinding
    private val game = GuessingGame()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(Looper.getMainLooper())

        binding.Guess.setOnClickListener {
            val guess_number = binding.editText.text.toString().toInt()
            val ans_str = game.Guess(guess_number)
            binding.textView.text = game.getRangeText()
            binding.textView.text = ans_str
            if (ans_str == "猜對了") {
                handler.postDelayed({
                    Toast.makeText(this, "六秒後重置", Toast.LENGTH_SHORT).show()
                    game.resetGame()
                    binding.textView.text = "再猜一次"
                    binding.textView.text = "下一回合"
                }, 6000)
            }
        }

        binding.Reset.setOnClickListener {
            game.resetGame()
            binding.textView.text = "再猜一次"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}