package com.example.coroutinestart.presentation.dataBinding

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

interface OnOptionClickListener{
    fun onOptionClick(option: Int)
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView,enough: Boolean){
    textView.setTextColor(getColorByState(textView.context,enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar,enough: Boolean){
    val color = getColorByState(progressBar.context,enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context,goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("numberAsText")
fun bindnumberAsText(textView: TextView,number: Int){
    textView.text =  number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView,clickListener: OnOptionClickListener){
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}