package ru.dillab.sportdiary.ui.tests.evening

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.dillab.sportdiary.domain.model.EveningResult
import ru.dillab.sportdiary.domain.use_case.DayResultUseCases
import ru.dillab.sportdiary.utils.generateIdFromLong
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EveningTestViewModel @Inject constructor(
    private val useCases: DayResultUseCases
) : ViewModel() {

    private val _productivity = MutableStateFlow<String?>(null)
    val productivity: StateFlow<String?> = _productivity

    val goals = MutableStateFlow<String?>(null)

    private val qualities = MutableStateFlow<String?>(null)

    val strength = MutableStateFlow<Boolean?>(null)
    val endurance = MutableStateFlow<Boolean?>(null)
    val plasticity = MutableStateFlow<Boolean?>(null)
    val coordination = MutableStateFlow<Boolean?>(null)
    val dexterity = MutableStateFlow<Boolean?>(null)
    val technic = MutableStateFlow<Boolean?>(null)

    init {
        viewModelScope.launch {
            useCases.getTodaysEveningResult().collect {
                _productivity.value = it?.productivity
                goals.value = it?.goals
                qualities.value = it?.qualities
            }
        }
        viewModelScope.launch {
            qualities.collect {
                strength.value = qualities.value?.contains(Qualities.STRENGTH.text)
                endurance.value = qualities.value?.contains(Qualities.ENDURANCE.text)
                plasticity.value = qualities.value?.contains(Qualities.PLASTICITY.text)
                coordination.value = qualities.value?.contains(Qualities.COORDINATION.text)
                dexterity.value = qualities.value?.contains(Qualities.DEXTERITY.text)
                technic.value = qualities.value?.contains(Qualities.TECHNIC.text)
            }
        }
    }

    fun updateDatabase() {
        val time = Calendar.getInstance().timeInMillis
        val id = time.generateIdFromLong()
        qualities.value = buildString {
            append(if (strength.value == true) "${Qualities.STRENGTH.text}, " else "")
            append(if (endurance.value == true) "${Qualities.ENDURANCE.text}, " else "")
            append(if (plasticity.value == true) "${Qualities.PLASTICITY.text}, " else "")
            append(if (coordination.value == true) "${Qualities.COORDINATION.text}, " else "")
            append(if (dexterity.value == true) "${Qualities.DEXTERITY.text}, " else "")
            append(if (technic.value == true) Qualities.TECHNIC.text else "")
        }.dropLastWhile { !it.isLetter() }

        viewModelScope.launch {
            useCases.addEveningResult(
                EveningResult(
                    id = id,
                    eveningTime = time,
                    productivity = productivity.value,
                    goals = goals.value,
                    qualities = qualities.value
                )
            )
        }
    }


    fun setProductivity(productivity: String) {
        _productivity.value = productivity
    }
}