package com.erbe.marsrealestate.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/*
[{"price":450000,
    "id":"424906",
    "type":"rent",
    "img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631300305227E03_DXXX.jpg"},
...]
*/

/**
 * This data class defines a Mars property which includes an ID, the image URL, the type (sale
 * or rental) and the price (monthly if it's a rental).
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
@Parcelize
data class MarsProperty(
    val id: String,
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "img_src") val imgSrcUrl: String,
    val type: String,
    val price: Double
) : Parcelable {
    val isRental
        get() = type == "rent"
}