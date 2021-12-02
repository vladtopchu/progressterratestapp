package ru.topchu.progressterra.presentation.bonusesinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.topchu.progressterra.databinding.FragmentBonusesBinding

@AndroidEntryPoint
class BonusesInfoFragment : Fragment() {

    private var _binding: FragmentBonusesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BonusesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBonusesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            if(viewState.isLoading) {
                binding.progressCircular.visibility = View.VISIBLE
            } else {
                binding.progressCircular.visibility = View.GONE
                if(viewState.errorMessage != null) {
                    Toast.makeText(requireContext(), viewState.errorMessage, Toast.LENGTH_LONG).show()
                } else if(viewState.response != null) {
                    binding.bonusesInfoView.apply {
                        viewState.response.data!!.apply {
                            currentBonuses = currentQuantity.toInt()
                            setBurningText(dateBurning, forBurningQuantity.toInt())
                        }
                    }
                }
            }
        }
    }
}