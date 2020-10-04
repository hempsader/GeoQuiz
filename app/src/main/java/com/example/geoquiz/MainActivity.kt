package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.geoquiz.data.Question

class MainActivity : AppCompatActivity() {
    private lateinit var buttonTrue: Button
    private lateinit var buttonFalse: Button
    private lateinit var buttonNext: Button
    private lateinit var textQuestion: TextView
    private var index = 0

    private val questions = listOf(Question(R.string.question_africa,false),Question(R.string.question_americas,true),
    Question(R.string.question_asia,true), Question(R.string.question_australia,true), Question(R.string.question_mideast,false),Question(R.string.question_oceans,true))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonTrue = findViewById(R.id.true_button)
        buttonFalse = findViewById(R.id.false_button)
        textQuestion = findViewById(R.id.question)
        buttonNext = findViewById(R.id.next_button)
        textQuestion.setText(questions[index].source)


        buttonTrue.setOnClickListener {
            answer(true)

        }
        buttonFalse.setOnClickListener {
            answer(false)
        }

        buttonNext.setOnClickListener {
            if(index == questions.size-1) index = 0 else index++
            textQuestion.setText(questions[index].source)
        }
    }
    fun answer(answer: Boolean) {
        if(questions[index].answer == answer){
            Toast.makeText(this,"True",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"False",Toast.LENGTH_SHORT).show()
        }
    }
}