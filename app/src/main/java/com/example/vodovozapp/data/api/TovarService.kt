package com.example.vodovozapp.data.api

import com.example.vodovozapp.data.model.TovaryList
import retrofit2.Response
import retrofit2.http.GET

interface TovarService {

    @GET("newmobile/glavnaya/super_top.php?action=topglav")
    suspend fun getTovary(): Response<TovaryList>
}