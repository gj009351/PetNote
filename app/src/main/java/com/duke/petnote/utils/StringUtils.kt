package com.duke.petnote.utils

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView

object StringUtils {
    fun getString(origin: String): String {
        return origin
            .replace("&nbsp;", " ")
            .replace("<BR>", "\n")
            .replace("<p>", "")
            .replace("</p>", "")
            .replace("<br>", "")
            .replace("<br/>", "")
            .replace("<br />", "")
            .replace("</font>", "")
            .replace("<font.*>".toRegex(), "")
    }

    fun setTextLast(
        context: Context,
        tv: TextView,
        before: String,
        last: String,
        color: Int
    ) {
        val spanString = SpannableString(before + last)
        spanString.setSpan(
            ForegroundColorSpan(context.resources.getColor(color)),
            before.length,
            (before + last).length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        ) // 设置前景色为洋红色
        spanString.setSpan(StyleSpan(Typeface.NORMAL), 0, before.length
            , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //正常
        spanString.setSpan(StyleSpan(Typeface.BOLD), before.length, (before + last).length
            , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //正常
        tv.text = spanString
    }

    fun setTextMiddle(
        context: Context,
        tv: TextView,
        before: String,
        center: String,
        last: String,
        color: Int
    ) {
        val spanString = SpannableString(before + center + last)
        spanString.setSpan(
            ForegroundColorSpan(context.resources.getColor(color)),
            before.length,
            (before + center).length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        ) // 设置前景色为洋红色
        tv.text = spanString
    }

    fun getStringFromList(list: List<String?>?, huanhang: Boolean): String {
        val sb = StringBuilder()
        if (list != null && !list.isEmpty()) {
            for (item in list) {
                sb.append(item)
                sb.append(if (huanhang) "\n" else " ")
            }
        }
        return sb.toString()
    }
}
