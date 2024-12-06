package com.duke.petnote.data.remote

import com.duke.petnote.data.Resource
import com.duke.petnote.data.dto.login.LoginResponse
import com.duke.petnote.data.dto.recipes.Recipes

/**
 * Created by AhmedEltaher
 */

internal interface RemoteDataSource {
    suspend fun requestRecipes(): Resource<Recipes>
    suspend fun listUser(): Resource<Recipes>
    suspend fun doLogin(phone: String, password: String): Resource<LoginResponse>
    suspend fun register(): Resource<Recipes>
}
