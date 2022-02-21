package ru.dillab.sportdiary.ui.results

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.dillab.sportdiary.data.local.entity.DayResultEntity
import ru.dillab.sportdiary.data.local.DayResultDao
import ru.dillab.sportdiary.domain.model.DayResult
import ru.dillab.sportdiary.domain.use_case.GetDayResults
import ru.dillab.sportdiary.utils.Resource
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(private val getDayResults: GetDayResults) : ViewModel() {

    private val _dayResults = MutableLiveData<List<DayResult>>()
    val dayResults: LiveData<List<DayResult>> = _dayResults

    init {
        Log.d("test", "init viewModel")
        loadDayResults()
    }

    private fun loadDayResults() {
        Log.d("test", "loadDayResults()")
        viewModelScope.launch {
            Log.d("test", "viewModelScope.launch")
            getDayResults().onEach { result ->
                Log.d("test", "getDayResults().onEach $result")
                when (result) {
                    is Resource.Loading -> {
                        Log.d("test", "Resource.Loading")
                        _dayResults.value = result.data ?: emptyList()
                    }
                    is Resource.Error -> {
                        Log.d("test", "Resource.Error")
                        _dayResults.value = result.data ?: emptyList()
                    }
                    is Resource.Success -> {
                        Log.d("test", "Resource.Success")
                        _dayResults.value = result.data ?: emptyList()
                    }
                }
            }.launchIn(this)
        }
    }
}

