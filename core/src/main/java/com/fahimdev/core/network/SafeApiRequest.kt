package com.fahimdev.core.network

import android.util.Log
//import com.fahimdev.composeboilerplate.BuildConfig
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection

object SafeApiRequest {
    suspend fun <T : Any?> apiRequest(call: suspend () -> Response<T>): ApiResult<T?> {
        return try {
            val response = call.invoke()

            if (response.isSuccessful &&
                response.code() in HttpURLConnection.HTTP_OK until HttpURLConnection.HTTP_MULT_CHOICE
            ) {
                ApiResult.Success(response.body())
            } else {
                val error = response.errorBody()?.string()
                val message = StringBuilder()
                error?.let {
                    try {
                        message.append(JSONObject(it).getString("message"))
                    } catch (e: JSONException) { /* Ignore */ }
                    message.append("\n")
                }
                message.append("Error Code: ${response.code()}")

                ApiResult.Error(message.toString(), response.code())
            }

        } catch (e: IOException) {
            Log.d("Network Error", "Network Error: ${e.localizedMessage}")
//            if (BuildConfig.DEBUG) {
//                Log.d("Network Error", "Network Error: ${e.localizedMessage}")
//            }
            ApiResult.NetworkError

        } catch (e: Exception) {
            Log.d("Network Error", "Unexpected Error: ${e.localizedMessage}")
//            if (BuildConfig.DEBUG) {
//                Log.d("Network Error", "Unexpected Error: ${e.localizedMessage}")
//            }
            ApiResult.Error(e.localizedMessage ?: "Unknown Error")
        }
    }
}