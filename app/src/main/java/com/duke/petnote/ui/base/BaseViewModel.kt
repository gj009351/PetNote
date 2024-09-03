package com.duke.petnote.ui.base

import androidx.lifecycle.ViewModel
import com.duke.petnote.usecase.errors.ErrorManager
import javax.inject.Inject


/**
 * Created by AhmedEltaher
 */


abstract class BaseViewModel : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: ErrorManager
}
