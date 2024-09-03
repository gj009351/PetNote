package com.duke.petnote.data

import com.duke.petnote.data.dto.recipes.Recipes
import com.duke.petnote.data.dto.login.LoginRequest
import com.duke.petnote.data.dto.login.LoginResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by AhmedEltaher
 */

interface DataRepositorySource {
    suspend fun requestRecipes(): Flow<Resource<Recipes>>
    suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>
    suspend fun addToFavourite(id: String): Flow<Resource<Boolean>>
    suspend fun removeFromFavourite(id: String): Flow<Resource<Boolean>>
    suspend fun isFavourite(id: String): Flow<Resource<Boolean>>
}
