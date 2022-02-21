package ru.dillab.sportdiary.data.remote

import retrofit2.http.GET
import ru.dillab.sportdiary.data.remote.dto.DayResultsDto

interface DayResultsApi {

    @GET("v4/spreadsheets/$spreadsheetId/values/Ответы?alt=json&key=$API_KEY")
    suspend fun getDataFromGoogleSheet(): DayResultsDto

    // TODO Implement post request to sheet
    // @POST("v4/spreadsheets/$spreadsheetId/formResponse")
    // @FormUrlEncoded
    // suspend fun updateGoogleSheetData(
    //     @Field("entry.188672219") name: String,
    //     @Field("entry.1859048068") second: String
    // )

    companion object {
        const val BASE_URL = "https://sheets.googleapis.com/"
        private const val spreadsheetId = "1hpmxE7BCFuAqs2tF50IyQ6B7k35ObHWDT83wzqLAGUM"
        private const val API_KEY = "AIzaSyDUesVR-k8TnpWi3Q_y9BxjLc-MsGjJLz4"
    }
}