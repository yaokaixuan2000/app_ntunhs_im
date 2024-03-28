package com.example.firstactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class PaperScissorsStone : AppCompatActivity() {
    private lateinit var txtCom: TextView
    private lateinit var txtResult: TextView
    private lateinit var btnScissors: ImageButton
    private lateinit var btnRock: ImageButton
    private lateinit var btnPaper: ImageButton
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_scissors_stone)


        val btnBacktoFirst = findViewById<Button>(R.id.btnBacktoFirst)
        val text = intent.getBundleExtra("key")?.getString("name").toString()

        btnBacktoFirst.setOnClickListener{
            finish()
        }


        txtCom = findViewById(R.id.txtCom)
        txtResult = findViewById(R.id.txtResult)
        btnScissors = findViewById(R.id.imageButtonScissors)
        btnRock = findViewById(R.id.imageButtonRock)
        btnPaper = findViewById(R.id.imageButtonPaper)
        imageView = findViewById(R.id.imageView)

        btnRock.setOnClickListener{
            playGame(Choice.ROCK)
        }
        btnScissors.setOnClickListener{
            playGame(Choice.SCISSORS)
        }
        btnPaper.setOnClickListener{
            playGame(Choice.PAPER)
        }

    }
    enum class Choice{
        SCISSORS , ROCK , PAPER
    }

    fun playGame(playerChoice:Choice){
        val choices = Choice.values()
        val computerChoice = choices[Random().nextInt(choices.size)]
        when{
            playerChoice == computerChoice -> {
                txtCom.setText(getChoiceString(computerChoice))
                imageView.setImageResource(getDrawableResource(computerChoice));
                txtResult.setText(R.string.draw)
            }
            (playerChoice == Choice.SCISSORS && computerChoice == Choice.PAPER)||
                    (playerChoice == Choice.ROCK && computerChoice == Choice.SCISSORS)||
                    (playerChoice == Choice.PAPER && computerChoice == Choice.ROCK) -> {
                txtCom.setText(getChoiceString(computerChoice))
                imageView.setImageResource(getDrawableResource(computerChoice));
                txtResult.setText(R.string.win)
            }
            else -> {
                txtCom.setText(getChoiceString(computerChoice))
                imageView.setImageResource(getDrawableResource(computerChoice));
                txtResult.setText(R.string.lose)
            }
        }
    }

    fun getChoiceString(choice: Choice):Int{
        return when(choice){
            Choice.SCISSORS -> R.string.scissors
            Choice.ROCK -> R.string.rock
            Choice.PAPER -> R.string.paper
        }
    }

    fun getDrawableResource(choice: Choice):Int{
        return when(choice){
            Choice.SCISSORS -> R.drawable.scissor
            Choice.ROCK -> R.drawable.rock
            Choice.PAPER -> R.drawable.paper
        }
    }

}