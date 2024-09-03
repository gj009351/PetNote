package com.duke.petnote.data.remote

import com.duke.petnote.data.Resource
import com.duke.petnote.data.dto.recipes.Recipes

/**
 * Created by AhmedEltaher
 */

internal interface RemoteDataSource {
    suspend fun requestRecipes(): Resource<Recipes>
}
