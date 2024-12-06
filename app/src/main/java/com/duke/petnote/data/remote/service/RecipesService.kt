package com.duke.petnote.data.remote.service

import com.duke.petnote.data.dto.login.LoginResponse
import com.duke.petnote.data.dto.recipes.RecipesItem
import com.duke.petnote.data.dto.recipes.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by AhmedEltaher
 */

interface RecipesService {
    @GET("recipes.json")
    suspend fun fetchRecipes(): Response<List<RecipesItem>>
    @GET("USER/data")
    suspend fun listUser(): Response<List<RecipesItem>>
    @POST("all")
    suspend fun login(@Body body: RequestBody): Response<LoginResponse>
    @POST("all")
    suspend fun register(): Response<List<RecipesItem>>
}
