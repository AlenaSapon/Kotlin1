package com.sapon.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var quizItems:List<QuizItem>
    private lateinit var startButton: Button
    private var activeQuiz: MutableMap<QuizItem, AnswerType>?=null
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quizItems = createQuestionList()
        startButton = findViewById<Button>(R.id.btn_quiz_start)
        startButton.setOnClickListener{
            if(activeQuiz==null){
                startNewQuiz()
            }
            else {
                TODO()
            }
            var currentQuizItem:QuizItem?=null
            activeQuiz!!.forEach {
                    (key, value) ->
                if (value==AnswerType.UNDEFINED) currentQuizItem=key}
            val newIntent= Intent(applicationContext,QuestionActivity::class.java)
            newIntent.putExtra(IntentKeys.KEY_QUESTION, currentQuizItem!!.question) // TODO
            newIntent.putExtra(IntentKeys.KEY_ANSWER1, currentQuizItem!!.answer.get(0))
            newIntent.putExtra(IntentKeys.KEY_ANSWER2, currentQuizItem!!.answer.get(1))
            newIntent.putExtra(IntentKeys.KEY_CORRECT_INDEX, currentQuizItem!!.correctId)
            startActivity(newIntent)
        }
    }

    private fun startNewQuiz() {
        activeQuiz = mutableMapOf()
       for (index in 0..3){
           activeQuiz!!.put(quizItems.get(index), AnswerType.UNDEFINED) //!! безусловное приведение к nonnullable
        }
    }
}

data class QuizItem(val question : String, val answer : List<String>, val correctId : Int)

class IntentKeys {
    companion object {
        val KEY_QUESTION: String="question"
        val KEY_ANSWER1:String="answer0"
        val KEY_ANSWER2:String="answer1"
        val KEY_CORRECT_INDEX:String="correctIndex"
        const val KEY_RESULT="KEY_RESULT"
    }
}

fun createQuestionList(): List<QuizItem>{
   // val quizItemList: List<QuizItem> = listOf() return immutable list
   val quizItemList: MutableList<QuizItem> = mutableListOf()
    quizItemList.add(QuizItem("Is Kotlin language with static?", listOf("Yes", "No"), 0))
    quizItemList.add(QuizItem("How many primitive types in Kotlin?", listOf("0","8"),1))
    quizItemList.add(QuizItem("Is Kotlin functional language?", listOf("Yes", "No"),1))
    quizItemList.add(QuizItem("What is a function in Kotlin?", listOf("Object", "Variable"),0))

    return quizItemList
}

enum class AnswerType{
    CORRECT, INCORRECT, UNDEFINED
}