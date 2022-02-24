package ru.dillab.sportdiary.ui.results

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.dillab.sportdiary.domain.model.DayResult
import ru.dillab.sportdiary.domain.use_case.DayResultUseCases
import ru.dillab.sportdiary.utils.ServerState
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(private val useCases: DayResultUseCases) : ViewModel() {

    private val _dayResults = MutableStateFlow<List<DayResult>>(emptyList())
    val dayResults: StateFlow<List<DayResult>> = _dayResults

    private val _statusBar = MutableStateFlow("")
    val statusBar: StateFlow<String> = _statusBar

    init {
        viewModelScope.launch {
            useCases.getDayResults().onEach { result ->
                when (result) {
                    is ServerState.Loading -> {
                        Log.d("testing", "ServerState.Success")
                        _dayResults.value = result.data ?: emptyList()
                        _statusBar.value = "Загружаю данные"
                    }
                    is ServerState.Error -> {
                        Log.d("testing", "ServerState.Success")
                        _dayResults.value = result.data ?: emptyList()
                        _statusBar.value = result.message ?: "Ошибка"
                    }
                    is ServerState.Success -> {
                        Log.d("testing", "ServerState.Success")
                        _dayResults.value = result.data ?: emptyList()
                        Log.d("testing", "${result.data}")
                        _statusBar.value = "Данные обновлены"
                    }
                }
            }.launchIn(this)
        }
    }
}

