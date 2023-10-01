package com.sapon.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        val question = intent.getStringExtra(IntentKeys.KEY_QUESTION ) // TODO
        val answer0 = intent.getStringExtra(IntentKeys.KEY_ANSWER1)
        val answer1 = intent.getStringExtra(IntentKeys.KEY_ANSWER2)
        val correctIndex = intent.getIntExtra(IntentKeys.KEY_CORRECT_INDEX, -1)
        Log.d(QuestionActivity::class.java.simpleName, "question: $question, answer1: $answer0 answer2: $answer1 correctIndex: $correctIndex")

    }
}