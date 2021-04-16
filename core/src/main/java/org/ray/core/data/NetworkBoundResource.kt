package org.ray.core.data

import kotlinx.coroutines.flow.*
import org.ray.core.data.remote.api.response.ResponseStatus

abstract class NetworkBoundResource<ResultType, RequestType> {
    protected open fun onFetchFailed() {}
    protected abstract fun loadFromDB(): Flow<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract suspend fun createCall(): Flow<ResponseStatus<RequestType>>
    protected abstract suspend fun saveCallResult(data: RequestType)

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()

        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())

            when (val status = createCall().first()) {
                is ResponseStatus.Success -> {
                    saveCallResult(status.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ResponseStatus.Error -> {
                    onFetchFailed()
                    emit(Resource.Failed<ResultType>(status.errorMessage))
                }
                is ResponseStatus.Empty -> emitAll(loadFromDB().map { Resource.Success(it) })
            }
        } else emitAll(loadFromDB().map { Resource.Success(it) })
    }

    fun asFlow(): Flow<Resource<ResultType>> = result
}