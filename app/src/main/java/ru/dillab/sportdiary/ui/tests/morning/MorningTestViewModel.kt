package ru.dillab.sportdiary.ui.tests.morning


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.dillab.sportdiary.data.local.entity.DayResultEntity
import ru.dillab.sportdiary.data.local.DayResultDao
import ru.dillab.sportdiary.generateIdFromLong
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MorningTestViewModel @Inject constructor() : ViewModel() {

    private val _sleepHours = MutableLiveData<String?>()
    val sleep: LiveData<String?> = _sleepHours

    val pulse: MutableLiveData<String?> = MutableLiveData<String?>()

    private val _musclePain = MutableLiveData<String?>()
    val musclePain: LiveData<String?> = _musclePain

    // fun updateDatabase() {
    //     val time = Calendar.getInstance().timeInMillis
    //     val id = time.generateIdFromLong()
    //     viewModelScope.launch {
    //         dayResultDao.insert(
    //             DayResultEntity(
    //                 id = id,
    //                 morningTime = time,
    //                 sleepHours = sleep.value,
    //                 pulse = pulse.value,
    //                 musclePain = musclePain.value
    //             )
    //         )
    //     }
    // }

    fun setSleepHours(sleepHours: String) {
        _sleepHours.value = sleepHours
    }

    fun setMusclePain(musclePain: String) {
        _musclePain.value = musclePain
    }

}








