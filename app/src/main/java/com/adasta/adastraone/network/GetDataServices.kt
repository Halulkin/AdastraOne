package com.adasta.adastraone.network

import com.adasta.adastraone.model.Character
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataServices {

    @GET("api/character/?")
    fun getList(@Query("page") pages: String): Observable<Response<Character>>

}