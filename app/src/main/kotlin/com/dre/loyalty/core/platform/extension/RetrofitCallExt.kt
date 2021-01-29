package com.dre.loyalty.core.platform.extension

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import retrofit2.Call

fun <T, R> Call<T>.request(transform: (T) -> R): Either<Failure, R> {
    return try {
        val response = this.execute()
        when (response.isSuccessful) {
            true -> Either.Right(transform((response.body()!!)))
            false -> Either.Left(Failure.ServerError)
        }
    } catch (exception: Throwable) {
        exception.printStackTrace()
        Either.Left(Failure.ServerError)
    }
}