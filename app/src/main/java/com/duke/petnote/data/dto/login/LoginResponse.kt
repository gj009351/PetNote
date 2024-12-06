package com.duke.petnote.data.dto.login

import android.os.Parcel
import com.fasterxml.jackson.annotation.JsonProperty

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Created by AhmedEltaher
 */
@JsonClass(generateAdapter = true)
@Parcelize
data class LoginResponse (
    @JsonProperty("success") val success: Boolean,
//    @JsonProperty("meta") val meta: Object,
    @JsonProperty("results") val results: List<Result>
) : Parcelable {
    /*data class Meta(
        @JsonProperty("served_by") val servedBy: String?,
        @JsonProperty("duration") val duration: Double,
        @JsonProperty("changes") val changes: Int,
//        @JsonProperty("last_row_id") val lastRowId: Int,
        @JsonProperty("changed_db") val changedDb: Boolean,
        @JsonProperty("size_after") val sizeAfter: Int,
        @JsonProperty("rows_read") val rowsRead: Int,
        @JsonProperty("rows_written") val rowsWritten: Int
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readDouble(),
            parcel.readInt(),
//            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(servedBy)
            parcel.writeDouble(duration)
            parcel.writeInt(changes)
//            parcel.writeInt(lastRowId)
            parcel.writeByte(if (changedDb) 1 else 0)
            parcel.writeInt(sizeAfter)
            parcel.writeInt(rowsRead)
            parcel.writeInt(rowsWritten)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Meta> {
            override fun createFromParcel(parcel: Parcel): Meta {
                return Meta(parcel)
            }

            override fun newArray(size: Int): Array<Meta?> {
                return arrayOfNulls(size)
            }
        }
    }*/

    data class Result(
        @JsonProperty("id") val id: Int,
        @JsonProperty("name") val name: String?,
        @JsonProperty("phone") val phone: String?,
        @JsonProperty("icon") val icon: String?,
        @JsonProperty("country") val country: String?,
        @JsonProperty("province") val province: String?,
        @JsonProperty("city") val city: String?,
        @JsonProperty("address") val address: String?,
        @JsonProperty("sex") val sex: String?,
        @JsonProperty("password") val password: String?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(name)
            parcel.writeString(phone)
            parcel.writeString(icon)
            parcel.writeString(country)
            parcel.writeString(province)
            parcel.writeString(city)
            parcel.writeString(address)
            parcel.writeString(sex)
            parcel.writeString(password)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Result> {
            override fun createFromParcel(parcel: Parcel): Result {
                return Result(parcel)
            }

            override fun newArray(size: Int): Array<Result?> {
                return arrayOfNulls(size)
            }
        }
    }
}