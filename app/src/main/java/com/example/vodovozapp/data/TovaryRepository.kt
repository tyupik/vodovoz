package com.example.vodovozapp.data

import com.example.vodovozapp.data.api.TovaryApi
import com.example.vodovozapp.data.model.TovaryList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TovaryRepository {

    suspend fun getTovary(): TovaryList =
        withContext(Dispatchers.IO) {
            val response = TovaryApi.retrofitService.getTovary()
            val body = response.body()!!
            return@withContext body
        }
}