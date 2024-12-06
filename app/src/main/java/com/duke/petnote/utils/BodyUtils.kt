package com.duke.petnote.utils

import com.duke.petnote.data.dto.recipes.RequestBody

object BodyUtils {
    fun login(phone: String, password: String): RequestBody {
        val body = RequestBody()
        //        body.query = "DELETE FROM 'USER' WHERE ID='2';";
//        body.query = "INSERT TO USER VALUES (" + phone + ", '" + password + "');";
        body.query = "SELECT * FROM USER WHERE PHONE = '$phone' AND PASSWORD ='$password';"
        return body
    } //    fun register(phone: String, password: String): BodyUtils {
    //        val body = BodyUtils()
    //        //        body.query = "DELETE FROM 'USER' WHERE ID='2';";
    //        body.query = "INSERT TO USER VALUES ($phone, '$password');"
    //        return body
    //    }
    //
    //    @JvmField
    //    val CREATOR: Creator<BodyUtils?> = object : Creator<BodyUtils?> {
    //        override fun createFromParcel(`in`: Parcel): BodyUtils? {
    //        return BodyUtils(`in`)
    //            }
    //
    //        override fun newArray(size: Int): Array<BodyUtils?> {
    //            return arrayOfNulls(size)
    //        }
    //    }
}
