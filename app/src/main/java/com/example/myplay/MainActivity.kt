package com.example.myplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var gameStarted = false
    private lateinit var countDownTimer: CountDownTimer
    private var initialCountDown: Long = 60000
    private var countDownInterval: Long = 1000 // chcemy aby co sekunde
    private var timeLeft = 60

    private var score = 0
    private lateinit var score_game: TextView
    private lateinit var tap_button : Button
    private lateinit var time_left: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        score_game = findViewById(R.id.score_game)
        tap_button = findViewById(R.id.tap_button)
        time_left = findViewById(R.id.time_left)

        tap_button.setOnClickListener {
            incrementScore()
        }
        resetGame()

    }


    private fun incrementScore(){
        if (!gameStarted){
            startGame()
        }
        score++

        val newScore = getString(R.string.Score, score)
        score_game.text = newScore


    }
    private fun resetGame() {
        score = 0
        val initialScore = getString(R.string.Score, score) // uzywamy val - stala
        score_game.text = initialScore

        val initialTimeLeft = getString(R.string.Time, timeLeft)
        time_left.text = initialTimeLeft

        gameStarted = false

        countDownTimer = object :
            CountDownTimer(initialCountDown, countDownInterval) { //od ilu odliczac i co ile
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished.toInt() / 1000 // ile zostalo milisec do konca = cczas w sek
                val timeString = getString(R.string.Time, timeLeft)
                time_left.text = timeString
            }

            override fun onFinish() {
                endGame()

            }
        }
    }
    private fun startGame(){
        countDownTimer.start()
        gameStarted = true


    }
    private fun endGame(){
        Toast.makeText(this,getString(R.string.game_over_message, score), Toast.LENGTH_LONG).show()  // wyskakujacy dymek Toast
        resetGame()
    }

}
