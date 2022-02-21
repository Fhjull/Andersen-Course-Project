package ru.dillab.sportdiary.ui.tests.evening

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.dillab.sportdiary.data.local.entity.DayResultEntity
import ru.dillab.sportdiary.data.local.DayResultDao
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EveningTestViewModel @Inject constructor() : ViewModel() {

    private val _productivity = MutableLiveData<String?>()
    val productivity: LiveData<String?> = _productivity

    val goals: MutableLiveData<String?> = MutableLiveData<String?>()

    private val _qualities = MutableLiveData<String?>()
    val qualities: LiveData<String?> = _qualities
    // TODO Implement qualities

    fun setProductivity(productivity: String) {
        _productivity.value = productivity
    }

    // fun updateDatabase() {
    //     val time = Calendar.getInstance().timeInMillis
    //     val id = time.generateId()
    //     val oldDayResult = dayResultDao.getById(id)
    //     val dayResult = DayResultEntity(
    //         id = id,
    //         eveningTime = time,
    //         productivity = productivity.value,
    //         goals = goals.value,
    //         qualities = qualities.value
    //     )
    //     viewModelScope.launch {
    //         val updateSuccessful = dayResultDao.update(dayResult)
    //         // dayResultDao.update() returns number of rows that were changed. If 0 rows changed,
    //         // that means there is no such primary key and morning test was not passed. In that
    //         // we create new row
    //         if (updateSuccessful == 0) {
    //             dayResultDao.insert(dayResult)
    //         }
    //     }
    // }
}

fun Long.generateId(): Int {
    return SimpleDateFormat("yyMMddHH", Locale.US).format(this).toInt()
}
