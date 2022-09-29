package com.palak.starwarsdemo.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.palak.starwarsdemo.R
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("height")
fun bindHeight(view: TextView, height: String) {
    try {
        view.text = view.context.getString(R.string.meters, height.toDouble() / 100)
    } catch (e: Exception) {
        e.printStackTrace()
        view.text = "Height ${view.context.getString(R.string.not_available)}"
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("mass")
fun bindMass(view: TextView, mass: String) {
    try {
        view.text = view.context.getString(R.string.kg, mass.toInt())
    } catch (e: Exception) {
        e.printStackTrace()
        view.text = "Mass ${view.context.getString(R.string.not_available)}"
    }
}

@BindingAdapter("createdDate")
fun bindCreatedDate(view: TextView, createdDate: String?) {

    createdDate?.let {
        val date = SimpleDateFormat(
            Constants.CHARACTER_CREATED_DATE_FORMAT,
            Locale.getDefault()
        ).parse(it)

        date?.let {
            val dateToPrint = SimpleDateFormat(
                Constants.CHARACTER_CREATED_DATE_FORMAT_PRINT,
                Locale.getDefault()
            ).format(it)
            view.text = view.context.getString(R.string.created_date, dateToPrint)
        }
    }
}
