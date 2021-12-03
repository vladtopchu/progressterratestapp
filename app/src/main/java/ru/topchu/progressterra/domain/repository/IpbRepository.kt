package ru.topchu.progressterra.domain.repository

import ru.topchu.iprobonusapi.dto.AvailableBonusesInfo
import ru.topchu.iprobonusapi.dto.ClientParamsWithGeo
import ru.topchu.iprobonusapi.dto.ResultAuth

interface IpbRepository {
    suspend fun getUsersAccessToken(clientParamsWithGeo: ClientParamsWithGeo): ResultAuth
    suspend fun getBonusesInfo(accessToken: String): AvailableBonusesInfo
}