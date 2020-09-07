package com.demo.demoplp.remote.api

import com.demo.demoplp.remote.model.PLPModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers


/**
 * Created by Rashida on 9/7/20.
 *
 */
interface PLPApi {

    @Headers("secret-key: \$2b\$10\$ldwbG.B/2hNRvS2dzXDxoO5P87sYGwoE02SliZIh.8vlvsSctGqF2")
    @GET("b/5f3a3fcf4d939910361666fe/latest")
    fun getList():Observable<List<PLPModel>>
}