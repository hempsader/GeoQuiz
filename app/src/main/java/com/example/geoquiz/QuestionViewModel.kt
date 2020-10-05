package com.example.geoquiz

import androidx.lifecycle.ViewModel
import com.example.geoquiz.data.Question

class QuestionViewModel: ViewModel() {
    var index = 0
    private val questions = listOf(
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_australia, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_oceans, true)
    )

    fun currentQuestion() = questions[index].source
    fun currentAnswer() = questions[index].answer
    fun currentIndex() {
        if(index >  questions.size - 1 ){
            index = 0
        }else if(index < 0){
            index = questions.size-1
        }
    }
 }