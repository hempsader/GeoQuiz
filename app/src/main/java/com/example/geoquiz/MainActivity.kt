package com.example.geoquiz

import android.content.res.Configuration
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    private lateinit var buttonTrue: Button
    private lateinit var buttonFalse: Button
    private lateinit var buttonNext: Button
    private lateinit var textQuestion: TextView
    private lateinit var buttonPrevious: Button
    private lateinit var imageButtonNext: ImageButton
    private lateinit var imageButtonPrevious: ImageButton
    private lateinit var viewModel: QuestionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonTrue = findViewById(R.id.true_button)
        buttonFalse = findViewById(R.id.false_button)
        textQuestion = findViewById(R.id.question)
        viewModel =  ViewModelProvider(this).get(QuestionViewModel().javaClass)



        if (Configuration.ORIENTATION_LANDSCAPE == resources.configuration.orientation) {
            imageButtonNext = findViewById(R.id.imageButton_next)
            imageButtonPrevious = findViewById(R.id.imageButton_previous)
            imageButtonPrevious.setOnClickListener {
                changeQuestion(IndexDirection.PREVIOUS)
            }

            imageButtonNext.setOnClickListener {
                changeQuestion(IndexDirection.NEXT)
            }
        } else {
            buttonNext = findViewById(R.id.next_button)
            buttonPrevious = findViewById(R.id.previous_button)
            buttonNext.setOnClickListener {
                changeQuestion(IndexDirection.NEXT)
            }

            buttonPrevious.setOnClickListener {
                changeQuestion(IndexDirection.PREVIOUS)
            }
        }

        textQuestion.apply {
            setText(viewModel.currentQuestion())
            setOnClickListener { changeQuestion(IndexDirection.NEXT) }
        }


        buttonTrue.setOnClickListener {
            answer(true)
            it.isClickable = false
            buttonFalse.isClickable = false
            if(!it.isClickable){
                it.backgroundTintMode = PorterDuff.Mode.CLEAR
            }
        }

        buttonFalse.setOnClickListener {
            answer(false)
            it.isClickable = false
            buttonTrue.isClickable = false
            if(!it.isClickable){
                it.backgroundTintMode = PorterDuff.Mode.CLEAR
            }
        }
    }


    private fun answer(answer: Boolean) {
        if(viewModel.currentAnswer() == answer){
            Toast.makeText(this,"True",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"False",Toast.LENGTH_SHORT).show()
        }
    }
    private fun changeQuestion(indexDirection: IndexDirection){
        when(indexDirection){
            IndexDirection.NEXT -> {
                textQuestion.setText(viewModel.currentQuestion())
                viewModel.index++
                viewModel.currentIndex()
                buttonTrue.isClickable = true
                buttonFalse.isClickable = true
                buttonTrue.backgroundTintMode = PorterDuff.Mode.DARKEN
                buttonFalse.backgroundTintMode = PorterDuff.Mode.DARKEN
            }
            IndexDirection.PREVIOUS -> {
                textQuestion.setText(viewModel.currentQuestion())
                viewModel.index --
                viewModel.currentIndex()
                buttonTrue.isClickable = true
                buttonFalse.isClickable = true
                buttonTrue.backgroundTintMode = null
                buttonFalse.backgroundTintMode = null
            }
        }

    }

    enum class IndexDirection{
        NEXT,
        PREVIOUS
    }
}

