package com.duke.petnote.data.dto.recipes


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class RequestBody (
    @Json(name = "query")
    var query: String = "",
) : Parcelable
