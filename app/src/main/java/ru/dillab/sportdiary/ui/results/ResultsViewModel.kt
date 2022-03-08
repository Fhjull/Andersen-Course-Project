package ru.dillab.sportdiary.ui.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    // TODO Remove hardcoded strings by proper implementation of DataBinding or entire change
    // of statusBar implementation
    init {
        viewModelScope.launch {
            useCases.getDayResults().onEach { result ->
                when (result) {
                    is ServerState.Loading -> {
                        _dayResults.value = result.data ?: emptyList()
                        _statusBar.value = "Загружаю данные"
                    }
                    is ServerState.Error -> {
                        _dayResults.value = result.data ?: emptyList()
                        _statusBar.value = result.message ?: "Ошибка"
                    }
                    is ServerState.Success -> {
                        _dayResults.value = result.data ?: emptyList()
                        _statusBar.value = "Данные обновлены"
                    }
                }
            }.launchIn(this)
        }
    }
}

