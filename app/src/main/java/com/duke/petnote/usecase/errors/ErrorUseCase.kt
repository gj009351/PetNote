package com.duke.petnote.usecase.errors

import com.duke.petnote.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
