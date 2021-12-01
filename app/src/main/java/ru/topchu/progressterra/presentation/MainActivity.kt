package ru.topchu.progressterra.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.topchu.iprobonusapi.dto.ClientParamsWithGeo
import ru.topchu.progressterra.databinding.ActivityMainBinding
import ru.topchu.progressterra.utils.Constants.CLIENT_ID
import ru.topchu.progressterra.utils.Constants.DEVICE_ID

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.accessToken.observe(this) { resultAuth ->
            if(resultAuth != null){
                if(resultAuth.accessToken != null){
                    binding.textView.text = resultAuth.accessToken
                    binding.secBtn.visibility = View.VISIBLE
                    binding.secBtn.setOnClickListener {
                        viewModel.getBonusesInfo(resultAuth.accessToken!!)
                    }
                }
            }
        }

        viewModel.bonusesInfo.observe(this) { bonusesInfo ->
            if(bonusesInfo != null){
                if(bonusesInfo.data != null){
                    Log.d("TEEEEEST", bonusesInfo.data.toString())
                    binding.bonusesInfoView.apply {
                        currentBonuses = bonusesInfo.data!!.currentQuantity.toInt()
                        setBurningText(bonusesInfo.data!!.dateBurning, bonusesInfo.data!!.forBurningQuantity.toInt())
                    }
                }
            }
        }

        binding.btn.setOnClickListener {
            viewModel.getUsersAccessToken(ClientParamsWithGeo(
                CLIENT_ID,
                "",
                "device",
                DEVICE_ID,
                0F,
                0F,
                0
            ))
        }
    }
}