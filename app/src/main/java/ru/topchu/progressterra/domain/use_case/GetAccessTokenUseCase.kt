package ru.topchu.progressterra.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.topchu.iprobonusapi.dto.ClientParamsWithGeo
import ru.topchu.iprobonusapi.dto.ResultAuth
import ru.topchu.progressterra.domain.repository.IpbRepository
import ru.topchu.progressterra.utils.Resource
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val repository: IpbRepository
) {
    operator fun invoke(clientParamsWithGeo: ClientParamsWithGeo): Flow<Resource<ResultAuth>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getUsersAccessToken(clientParamsWithGeo)
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