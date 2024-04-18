package com.example.guess_number
import java.util.*

class GuessingGame {
    private var secret = Random().nextInt(100) + 1
    var maxNum = 100
    var minNum = 0

    fun Guess(guess: Int): String {
        if (guess > maxNum || guess < minNum) {
            return "${minNum}~${maxNum}"
        }

        when {
            guess > secret -> {
                maxNum = guess
                return "${minNum}~${maxNum}"
            }
            guess < secret -> {
                minNum = guess
                return "${minNum}~${maxNum}"
            }
            else -> {
                resetGame()
                return "猜對了"
            }
        }
    }

    fun getRangeText(): String = "$minNum~$maxNum"

    fun resetGame() {
        secret = Random().nextInt(100) + 1
        maxNum = 100
        minNum = 0
    }

}
