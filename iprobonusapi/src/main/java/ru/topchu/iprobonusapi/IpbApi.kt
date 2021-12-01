package ru.topchu.iprobonusapi

import retrofit2.http.*
import ru.topchu.iprobonusapi.dto.AvaliableBonusesInfo
import ru.topchu.iprobonusapi.dto.ClientParamsWithGeo
import ru.topchu.iprobonusapi.dto.ResultAuth

interface IpbApi {
    @POST("clients/accesstoken")
    suspend fun getUsersAccessToken(@Header("AccessKey") accessKey: String, @Body clientParams: ClientParamsWithGeo): ResultAuth

    @GET("ibonus/generalinfo/{accessToken}")
    suspend fun getBonusesInfo(@Header("AccessKey") accessKey: String, @Path("accessToken") accessToken: String): AvaliableBonusesInfo
}