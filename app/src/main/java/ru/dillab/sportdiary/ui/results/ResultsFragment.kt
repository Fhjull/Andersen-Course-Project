package ru.dillab.sportdiary.ui.results

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.dillab.sportdiary.databinding.FragmentResultsBinding

@AndroidEntryPoint
class ResultsFragment : Fragment() {

    private val viewModel: ResultsViewModel by viewModels()

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupStatusBar()
        setupRecycleView()
    }

    private fun setupStatusBar() {
        lifecycleScope.launch {
            viewModel.statusBar.collect {
                binding.resultsStatusBar.text = it
            }
        }
    }

    private fun setupRecycleView() {
        val recycleView = binding.resultsRecycleView
        recycleView.layoutManager = LinearLayoutManager(this.context)
        val adapter = ResultsAdapter()
        recycleView.adapter = adapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dayResults.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}