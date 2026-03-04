package com.flavorfusion.common_domain.model

inline fun <T> combineResults(
    vararg results: Result<List<T>?>
): Result<List<T>> {

    val firstError = results.firstOrNull { it is Result.Error }
    if (firstError is Result.Error) return Result.Error(firstError.error)

    val combined = results
        .filterIsInstance<Result.Success<List<T>?>>()
        .flatMap { it.data.orEmpty() }

    return Result.Success(combined)
}