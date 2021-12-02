package ru.topchu.progressterra.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.topchu.iprobonusapi.dto.AvaliableBonusesInfo
import ru.topchu.iprobonusapi.dto.ClientParamsWithGeo
import ru.topchu.iprobonusapi.dto.ResultAuth
import ru.topchu.progressterra.utils.Resource

interface IpbRepository {
    fun getUsersAccessToken(clientParamsWithGeo: ClientParamsWithGeo): Flow<Resource<ResultAuth>>
    fun getBonusesInfo(accessToken: String): Flow<Resource<AvaliableBonusesInfo>>
}