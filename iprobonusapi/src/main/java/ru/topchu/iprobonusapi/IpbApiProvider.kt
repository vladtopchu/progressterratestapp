package ru.topchu.iprobonusapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import ru.topchu.iprobonusapi.dto.AvaliableBonusesInfo
import ru.topchu.iprobonusapi.dto.ClientParamsWithGeo
import ru.topchu.iprobonusapi.dto.ResultAuth

sealed class IpbApiProvider {
    companion object {
        private const val BASE_URL = "https://mp1.iprobonus.com/api/v3/"
        operator fun invoke(): IpbApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IpbApi::class.java)
    }
}