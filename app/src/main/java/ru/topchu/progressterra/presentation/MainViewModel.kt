package ru.topchu.progressterra.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.topchu.iprobonusapi.IpbApi
import ru.topchu.iprobonusapi.dto.AvaliableBonusesInfo
import ru.topchu.iprobonusapi.dto.ClientParamsWithGeo
import ru.topchu.iprobonusapi.dto.ResultAuth
import ru.topchu.progressterra.utils.Constants.API_KEY
import ru.topchu.progressterra.utils.asLiveData
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: IpbApi
) : ViewModel() {
    private val _accessToken: MutableLiveData<ResultAuth> = MutableLiveData(null)
    val accessToken = _accessToken.asLiveData()

    private val _bonusesInfo: MutableLiveData<AvaliableBonusesInfo> = MutableLiveData(null)
    val bonusesInfo = _bonusesInfo.asLiveData()

    fun getUsersAccessToken(clientParams: ClientParamsWithGeo) {
        viewModelScope.launch {
            _accessToken.postValue(api.getUsersAccessToken(API_KEY, clientParams))
        }
    }

    fun getBonusesInfo(accessToken: String) {
        viewModelScope.launch {
            _bonusesInfo.postValue(api.getBonusesInfo(API_KEY, accessToken))
        }
    }
}