package com.jimmy.avowsstore.core.network

import com.google.gson.JsonParseException
import com.jimmy.avowsstore.BuildConfig
import com.jimmy.avowsstore.core.data.DataError
import com.jimmy.avowsstore.core.data.Result
import java.io.IOException
import java.net.SocketTimeoutException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException


suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T, DataError.HttpError> {
    val response = try {
        execute()
    } catch (e: IOException) {
        return Result.Error(DataError.HttpError.NO_INTERNET)
    }catch (e: UnresolvedAddressException) {
        return Result.Error(DataError.HttpError.NO_INTERNET)
    }catch (e: SocketTimeoutException){
        return Result.Error(DataError.HttpError.CONNECTION_TIMEOUT)
    } catch (e: JsonParseException) {
        return Result.Error(DataError.HttpError.SERIALIZATION)
    } catch (e: Exception) {
        return Result.Error(DataError.HttpError.UNKNOWN)
    }
    return responseToResult(response)
}

suspend inline fun <reified T> safeCall(defaultValue: T, execute: () -> HttpResponse): Result<T, DataError.HttpError> {
    val response = try {
        execute()
    } catch (e: Exception) {
        return Result.Success(defaultValue)
    }
    return responseToResult(defaultValue, response)
}


suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError.HttpError> {
    //if (response.body() == null && response.isSuccessful) return Result.Success(Unit as T)
    return when(response.status.value) {
        in 200..299 -> Result.Success(response.body<T>()!!)
        401 -> Result.Error(DataError.HttpError.UNAUTHORIZED)
        403 -> Result.Error(DataError.HttpError.FORBIDDEN)
        404 -> Result.Error(DataError.HttpError.NOT_FOUND)
        408 -> Result.Error(DataError.HttpError.REQUEST_TIMEOUT)
        413 -> Result.Error(DataError.HttpError.PAYLOAD_TOO_LARGE)
        429 -> Result.Error(DataError.HttpError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.HttpError.SERVER_ERROR)
        else -> Result.Error(DataError.HttpError.UNKNOWN)
    }
}


suspend inline fun <reified T> responseToResult(defaultValue: T, response: HttpResponse): Result<T, DataError.HttpError> {
    //if (response.body() == null && response.isSuccessful) return Result.Success(Unit as T)
    return when(response.status.value) {
        in 200..299 -> Result.Success(response.body<T>()!!)
        else -> Result.Success(defaultValue)
    }
}


fun constructRoute(route: String): String {
    return when {
        route.contains(BuildConfig.BASE_URL) -> route
        route.startsWith("/") -> BuildConfig.BASE_URL + route
        else -> BuildConfig.BASE_URL + "/$route"
    }
}
