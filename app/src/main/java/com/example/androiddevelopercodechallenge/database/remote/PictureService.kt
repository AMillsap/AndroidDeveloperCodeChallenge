package com.example.androiddevelopercodechallenge.database.remote

import com.example.androiddevelopercodechallenge.model.pictureresponse.PictureResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface PictureService
{
    companion object{
        fun getPictureCallService() =
            RetrofitHelper.retrofitInstance.create(PictureService::class.java)
    }

    @GET("v2/list")
    fun getPictureList()
            : Deferred<ArrayList<PictureResult>>

}
