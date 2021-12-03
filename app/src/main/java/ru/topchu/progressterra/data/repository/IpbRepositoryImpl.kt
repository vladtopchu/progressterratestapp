package ru.topchu.progressterra.data.repository

import ru.topchu.iprobonusapi.IpbApi
import ru.topchu.iprobonusapi.dto.AvailableBonusesInfo
import ru.topchu.iprobonusapi.dto.ClientParamsWithGeo
import ru.topchu.iprobonusapi.dto.ResultAuth
import ru.topchu.progressterra.domain.repository.IpbRepository

class IpbRepositoryImpl(
    private val api: IpbApi
) : IpbRepository {

    override suspend fun getUsersAccessToken(clientParamsWithGeo: ClientParamsWithGeo): ResultAuth =
        api.getUsersAccessToken(clientParamsWithGeo)

    override suspend fun getBonusesInfo(accessToken: String): AvailableBonusesInfo =
        api.getBonusesInfo(accessToken)
}