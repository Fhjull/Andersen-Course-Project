package ru.dillab.sportdiary.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.dillab.sportdiary.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainMenuTestsCard.setOnClickListener { openTestsMenu() }
        binding.mainMenuResultsCard.setOnClickListener { openResults() }

    }

    private fun openResults() {
        val action = MainFragmentDirections.actionMainFragmentToResultsFragment()
        findNavController().navigate(action)
    }

    private fun openTestsMenu() {
        val action = MainFragmentDirections.actionMainFragmentToTestsMenuFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}