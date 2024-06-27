package com.jltech.wscars.utils


import com.jltech.wscars.utils.Validation.isEmailValid


fun String.isValidEmail(): Boolean {
    return isEmailValid(this)
}



