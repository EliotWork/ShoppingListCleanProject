package com.example.coroutinestart.presentation.dataBinding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.coroutinestart.R
import com.example.coroutinestart.domain.entity.GameResult


@BindingAdapter("requiredAnswers")
fun bindRequieredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scoreRequiredPercentage")
fun bindScoreRequiredPercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)

    )
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult){
    if (countOfQuestion == 0){
        0
    }else{
        ((countOfRightAnswers / countOfQuestion.toDouble())* 100).toInt()
    }
}

@BindingAdapter("imageWin")
fun bindImageView(imageView: ImageView,winner: Boolean){
    imageView.setImageResource(getSmileResId(winner))
}

private fun getSmileResId(winner: Boolean): Int{
    return if(winner){
        R.drawable.ic_smile
    } else{
        R.drawable.ic_sad
    }
}