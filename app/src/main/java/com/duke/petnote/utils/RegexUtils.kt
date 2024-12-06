package com.duke.petnote.utils

import java.util.regex.Pattern


/**
 * Created by ahmedeltaher on 14/10/2017.
 */

object RegexUtils {
    private val EMAIL_ADDRESS: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    )
    private val PHONE_NUMBER: Pattern = Pattern.compile("^1[3-9]\\d{9}\$")

    fun isValidEmail(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email).matches()
    }
    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        // 正则表达式：以1开头，第二位是3-9之间的数字，后面跟9位数字
        return phoneNumber.matches(PHONE_NUMBER.toRegex())
    }
}
