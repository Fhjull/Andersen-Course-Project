package ru.dillab.sportdiary.ui.tests.morning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.dillab.sportdiary.databinding.FragmentMorningTestBinding

@AndroidEntryPoint
class MorningTestFragment : Fragment() {

    private val morningViewModel: MorningTestViewModel by viewModels()

    private var _binding: FragmentMorningTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMorningTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = morningViewModel
        }

        binding.morningTestFinishButton.setOnClickListener { onFinishButtonClicked() }
    }

    private fun onFinishButtonClicked() {
        morningViewModel.updateDatabase()
        moveToResultsFragment()
    }

    private fun moveToResultsFragment() {
        val action = MorningTestFragmentDirections.actionMorningTestFragmentToResultsFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}