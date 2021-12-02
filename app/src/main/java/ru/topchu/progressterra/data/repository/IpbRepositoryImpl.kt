package ru.topchu.progressterra.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.topchu.iprobonusapi.IpbApi
import ru.topchu.iprobonusapi.dto.AvaliableBonusesInfo
import ru.topchu.iprobonusapi.dto.ClientParamsWithGeo
import ru.topchu.iprobonusapi.dto.ResultAuth
import ru.topchu.progressterra.domain.repository.IpbRepository
import ru.topchu.progressterra.utils.Resource
import timber.log.Timber
import java.io.IOException

class IpbRepositoryImpl(
    private val api: IpbApi
) : IpbRepository {

    override fun getUsersAccessToken(clientParamsWithGeo: ClientParamsWithGeo) :
            Flow<Resource<ResultAuth>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getUsersAccessToken(clientParamsWithGeo)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            Timber.d(e.toString())
            emit(Resource.Error(
                message = "Сервер ответил ошибкой"
            ))
        } catch (e: IOException) {
            Timber.d(e.toString())
            emit(Resource.Error(
                message = "Проверьте интернет-соединение"
            ))
        }
    }

    override fun getBonusesInfo(accessToken: String) :
            Flow<Resource<AvaliableBonusesInfo>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getBonusesInfo(accessToken)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            Timber.d(e.toString())
            emit(Resource.Error(
                message = "Сервер ответил ошибкой"
            ))
        } catch (e: IOException) {
            Timber.d(e.toString())
            emit(Resource.Error(
                message = "Проверьте интернет-соединение"
            ))
        }
    }
}