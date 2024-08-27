package com.example.vodovozapp.data

import com.example.vodovozapp.data.api.TovarService
import com.example.vodovozapp.data.api.TovaryApi
import com.example.vodovozapp.data.model.Tovary
import com.example.vodovozapp.data.model.TovaryList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TovaryRepository {

    suspend fun getTovary(): Unit =
        withContext(Dispatchers.IO) {
            val response = TovaryApi.retrofitService.getTovary()
            val body = response.body()!!.tovary

        }
}