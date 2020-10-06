package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class CheatActivity : AppCompatActivity() {
    private lateinit var buttonCheat: Button
    private lateinit var question: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        buttonCheat = findViewById(R.id.button_cheat)
        question = findViewById(R.id.question)
        val viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)

        val index = intent.extras?.getInt("key") ?: 0
        question.setText(viewModel.questionIndex(index))

        buttonCheat.setOnClickListener {
            val intent = intent.putExtra("result","SUPER")
            setResult(RESULT_OK,intent)
            finish()
        }
    }

}