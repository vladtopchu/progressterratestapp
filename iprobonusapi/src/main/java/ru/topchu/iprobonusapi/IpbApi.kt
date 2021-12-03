package ru.topchu.iprobonusapi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import ru.topchu.iprobonusapi.dto.AvailableBonusesInfo
import ru.topchu.iprobonusapi.dto.ClientParamsWithGeo
import ru.topchu.iprobonusapi.dto.ResultAuth
import ru.topchu.iprobonusapi.utils.HeaderInterceptor
import java.lang.IllegalStateException

interface IpbApi {
    @POST("clients/accesstoken")
    suspend fun getUsersAccessToken(@Body clientParams: ClientParamsWithGeo): ResultAuth

    @GET("ibonus/generalinfo/{accessToken}")
    suspend fun getBonusesInfo(@Path("accessToken") accessToken: String): AvailableBonusesInfo

    data class Builder(private var _accessKey: String? = null) {
        fun setAccessKey(accessKey: String) = apply { this._accessKey = accessKey }
        fun build(): IpbApi {
            if(_accessKey == null) {
                throw IllegalStateException("AccessKey is required")
            } else {
                val okHttpClient = OkHttpClient()
                    .newBuilder()
                    .addInterceptor(HeaderInterceptor(_accessKey!!))
                    .build()
                return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(IpbApi::class.java)
            }
        }
    }

    companion object {
        private const val BASE_URL = "https://mp1.iprobonus.com/api/v3/"
    }
}