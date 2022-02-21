package ru.dillab.sportdiary

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.dillab.sportdiary.data.local.DayResultsRoomDatabase

@HiltAndroidApp
class SportDiaryApplication : Application()