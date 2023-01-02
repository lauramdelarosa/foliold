package com.delarosa.folio.core.presentation

import android.util.Log
import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> = try {
    call.invoke()
} catch (e: Exception) {
    Log.e("safeApiCall", e.message.toString())
    Result.failure(IOException(errorMessage, e))
}

fun <T : Any> Response<T>.callServices(): Result<T> {
    if (this.isSuccessful) {
        return if (this.body() != null) {
            Result.success(this.body() as T)
        } else {
            Result.failure(NullBodyException(this.code(), this.errorBody()?.string().toString()))
        }
    }
    return Result.failure(ClientErrorsException(this.code(), this.errorBody()?.string().toString()))
}

class NullBodyException(val code: Int, val errorBody: String) : Throwable()

class ClientErrorsException(val code: Int, val errorBody: String) : Throwable()
