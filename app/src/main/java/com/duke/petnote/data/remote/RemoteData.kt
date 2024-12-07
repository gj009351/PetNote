package com.duke.petnote.data.remote

import com.duke.petnote.data.Resource
import com.duke.petnote.data.dto.login.LoginResponse
import com.duke.petnote.data.dto.recipes.Recipes
import com.duke.petnote.data.dto.recipes.RecipesItem
import com.duke.petnote.data.dto.recipes.RequestBody
import com.duke.petnote.data.error.CHECK_YOUR_FIELDS
import com.duke.petnote.utils.BodyUtils
import com.duke.petnote.data.error.NETWORK_ERROR
import com.duke.petnote.data.error.NO_INTERNET_CONNECTION
import com.duke.petnote.data.remote.service.RecipesService
import com.duke.petnote.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by AhmedEltaher
 */

class RemoteData @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity) : RemoteDataSource {
    override suspend fun doLogin(phone: String, password: String): Resource<LoginResponse> {
        val recipesService = serviceGenerator.createService(RecipesService::class.java)
        val body = BodyUtils.login(phone, password)
        return when (val response = processCall { recipesService.login(body) }) {
            is LoginResponse -> {
                if (response.success && response.results.isNotEmpty()) {
                    Resource.Success(data = response)
                } else {
                    Resource.DataError(errorCode = CHECK_YOUR_FIELDS)
                }
            }
            else -> {
                Resource.DataError(errorCode = CHECK_YOUR_FIELDS)
            }
        }
    }

    override suspend fun register(): Resource<Recipes> {
        val recipesService = serviceGenerator.createService(RecipesService::class.java)
        return when (val response = processCall(recipesService::register)) {
            is List<*> -> {
                Resource.Success(data = Recipes(response as ArrayList<RecipesItem>))
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }
    override suspend fun listUser(): Resource<Recipes> {
        val recipesService = serviceGenerator.createService(RecipesService::class.java)
        return when (val response = processCall(recipesService::listUser)) {
            is List<*> -> {
                Resource.Success(data = Recipes(response as ArrayList<RecipesItem>))
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun requestRecipes(): Resource<Recipes> {
        val recipesService = serviceGenerator.createService(RecipesService::class.java)
        return when (val response = processCall(recipesService::fetchRecipes)) {
            is List<*> -> {
                Resource.Success(data = Recipes(response as ArrayList<RecipesItem>))
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
