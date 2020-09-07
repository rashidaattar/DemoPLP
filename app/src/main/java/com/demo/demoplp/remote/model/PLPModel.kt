package com.demo.demoplp.remote.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Rashida on 9/7/20.
 *
 */

data class PLPModel(
    @SerializedName("id")
    var id:Int,
    @SerializedName("brand")
    var brand:String,
    @SerializedName("phone")
    var phone:String,
    @SerializedName("picture")
    var picture:String,
    @SerializedName("priceEur")
    var priceEur:String
)