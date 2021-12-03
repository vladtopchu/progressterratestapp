package ru.topchu.progressterra.presentation.bonusesinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.topchu.iprobonusapi.dto.AvailableBonusesInfo
import ru.topchu.iprobonusapi.dto.ClientParamsWithGeo
import ru.topchu.progressterra.domain.use_case.GetAccessTokenUseCase
import ru.topchu.progressterra.domain.use_case.GetBonusesInfoUseCase
import ru.topchu.progressterra.utils.Constants.CLIENT_ID
import ru.topchu.progressterra.utils.Constants.DEVICE_ID
import ru.topchu.progressterra.utils.Resource
import ru.topchu.progressterra.utils.ViewState
import ru.topchu.progressterra.utils.asLiveData
import javax.inject.Inject

@HiltViewModel
class BonusesViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getBonusesInfoUseCase: GetBonusesInfoUseCase
) : ViewModel() {

    private val _state = MutableLiveData(ViewState<AvailableBonusesInfo>())
    val state = _state.asLiveData()

    private var job: Job? = null

    init {
        job?.cancel()
        job = viewModelScope.launch {

            val clientParams = ClientParamsWithGeo(
                CLIENT_ID,
                "",
                "device",
                DEVICE_ID,
                0F,
                0F,
                0
            )

            getAccessTokenUseCase(clientParams).onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        getBonusesInfo(result.data?.accessToken!!)
                    }
                    is Resource.Loading -> {
                        _state.postValue(state.value?.copy(
                            isLoading = true
                        ))
                    }
                    is Resource.Error -> {
                        _state.postValue(state.value?.copy(
                            isLoading = false,
                            errorMessage = result.message
                        ))
                    }
                    else -> {}
                }
            }.launchIn(this)
        }
    }

    private fun getBonusesInfo(accessToken: String){
        job?.cancel()
        job = viewModelScope.launch {
            getBonusesInfoUseCase(accessToken).onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.postValue(state.value?.copy(
                            isLoading = false,
                            response = result.data
                        ))
                    }
                    is Resource.Loading -> {
                        _state.postValue(state.value?.copy(
                            isLoading = true
                        ))
                    }
                    is Resource.Error -> {
                        _state.postValue(state.value?.copy(
                            isLoading = false,
                            errorMessage = result.message
                        ))
                    }
                    else -> {}
                }
            }.launchIn(this)
        }
    }
}