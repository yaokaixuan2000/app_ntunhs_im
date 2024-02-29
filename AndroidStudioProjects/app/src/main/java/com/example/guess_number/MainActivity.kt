package com.example.guess_number

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val textView = findViewById<TextView>(R.id.textView)
    val editText = findViewById<EditText>(R.id.editText)
    val Guess = findViewById<Button>(R.id.Guess)
    val Reset = findViewById<Button>(R.id.Reset)
    var validate_num:Int
    var secret:Int = Random().nextInt(100) + 1
    var maxNum:Int = 100
    var minNum:Int = 0
    var guess_number:Int

    Guess.setOnClickListener{
        textView.text = editText.text
        guess_number = editText.text.toString().toInt()
        validate_num = editText.text.toString().toInt()-secret
        var ans_str:String="你猜對了"
        if(guess_number <= 100 && guess_number >= 0){
            if(validate_num>0 )
            {
                ans_str = "大"
                maxNum = guess_number
                textView.text = minNum.toString()+'-'+maxNum.toString()
            }else if(validate_num<0 ){
                ans_str = "小"
                minNum = guess_number
                textView.text = minNum.toString()+'-'+maxNum.toString()
            }else{
                textView.text = ans_str
            }
        }else{
            textView.text = "輸入100內數字"
        }

        Toast.makeText(this,ans_str,Toast.LENGTH_LONG).show()
    }
    Reset.setOnClickListener{
        secret = Random().nextInt(100)+1
        textView.text = "請輸入數字"
        minNum = 0
        maxNum = 100
    }

    }
}