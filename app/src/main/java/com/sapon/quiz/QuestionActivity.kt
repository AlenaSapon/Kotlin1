package com.sapon.quiz

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView

class QuestionActivity : AppCompatActivity() {

    lateinit var submitButton: Button
    lateinit var questionView: TextView
    lateinit var answer1View: TextView
    lateinit var answer2View: TextView
    lateinit var card1View: CardView
    lateinit var card2View: CardView
    private var result = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        submitButton = findViewById(R.id.btn_submit)
        questionView = findViewById(R.id.tv_question)
        answer1View = findViewById(R.id.tv_answer_1)
        answer2View = findViewById(R.id.tv_answer_2)
        card1View = findViewById(R.id.cv_answer_1)
        card2View = findViewById(R.id.cv_answer_2)
        submitButton.isEnabled=false


        val question = intent.getStringExtra(IntentKeys.KEY_QUESTION) // TODO
        val answer1 = intent.getStringExtra(IntentKeys.KEY_ANSWER1)
        val answer2 = intent.getStringExtra(IntentKeys.KEY_ANSWER2)
        val correctIndex = intent.getIntExtra(IntentKeys.KEY_CORRECT_INDEX, -1)
        Log.d(
            QuestionActivity::class.java.simpleName,
            "question: $question, answer1: $answer1 answer2: $answer2 correctIndex: $correctIndex"
        )

        questionView.text = question ?: "Any error accrued"
        answer1View.text = answer1
        answer2View.text = answer2

        answer1View.setOnClickListener {
            card1View.elevation = 0F
            card2View.elevation = 10F
            result = correctIndex == 0
            submitButton.isEnabled=true
        }
        answer2View.setOnClickListener {
            card2View.elevation = 0F
            card1View.elevation = 10F
            result = correctIndex == 1
            submitButton.isEnabled=true
        }
        submitButton.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra(IntentKeys.KEY_RESULT, result)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }


    }
}