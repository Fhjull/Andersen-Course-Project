package ru.dillab.sportdiary.ui.tests.evening

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.dillab.sportdiary.SportDiaryApplication
import ru.dillab.sportdiary.databinding.FragmentEveningTestBinding

@AndroidEntryPoint
class EveningTestFragment : Fragment() {

    private val eveningViewModel: EveningTestViewModel by viewModels()

    private var _binding: FragmentEveningTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEveningTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = eveningViewModel
        }

        binding.eveningTestFinishButton.setOnClickListener { onFinishButtonClicked() }
    }

    private fun onFinishButtonClicked() {
        // eveningViewModel.updateDatabase()
        moveToResultsFragment()
    }

    private fun moveToResultsFragment() {
        val action = EveningTestFragmentDirections.actionEveningTestFragmentToResultsFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}