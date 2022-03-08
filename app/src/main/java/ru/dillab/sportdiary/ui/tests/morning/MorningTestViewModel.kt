package ru.dillab.sportdiary.ui.tests.morning


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.dillab.sportdiary.domain.model.MorningResult
import ru.dillab.sportdiary.domain.use_case.DayResultUseCases
import ru.dillab.sportdiary.utils.generateIdFromLong
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MorningTestViewModel @Inject constructor(
    private val useCases: DayResultUseCases
) : ViewModel() {

    private val _sleepHours = MutableStateFlow<String?>(null)
    val sleep: StateFlow<String?> = _sleepHours

    val pulse = MutableStateFlow<String?>(null)

    private val _musclePain = MutableStateFlow<String?>(null)
    val musclePain: StateFlow<String?> = _musclePain

    init {
        viewModelScope.launch {
            useCases.getTodaysMorningResult().collect {
                _sleepHours.value = it?.sleepHours
                pulse.value = it?.pulse
                _musclePain.value = it?.musclePain
            }
        }
    }

    fun updateDatabase() {
        val time = Calendar.getInstance().timeInMillis
        val id = time.generateIdFromLong()
        viewModelScope.launch {
            useCases.addMorningResult(
                MorningResult(
                    id = id,
                    morningTime = time,
                    sleepHours = sleep.value,
                    pulse = pulse.value,
                    musclePain = musclePain.value
                )
            )
        }
    }

    fun setSleepHours(sleepHours: String) {
        _sleepHours.value = sleepHours
    }

    fun setMusclePain(musclePain: String) {
        _musclePain.value = musclePain
    }

}








