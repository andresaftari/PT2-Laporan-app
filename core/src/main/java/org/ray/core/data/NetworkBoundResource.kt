package org.ray.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.ray.core.data.remote.api.response.ResponseStatus

abstract class NetworkBoundResource<ResultType, RequestType> {
    protected open fun onFetchFailed() {}
    protected abstract suspend fun createCall(): Flow<ResponseStatus<RequestType>>
    protected abstract suspend fun saveCallResult(data: RequestType)

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.loading())
        when (val status = createCall().first()) {
            is ResponseStatus.Success -> {
                saveCallResult(status.data)
            }
            is ResponseStatus.Error -> {
                onFetchFailed()
                emit(Resource.error<ResultType>(status.errorMessage))
            }
            is ResponseStatus.Empty -> {
                onFetchFailed()
                emit(Resource.error<ResultType>("No data!"))
            }
        }
    }

    fun asFlow(): Flow<Resource<ResultType>> = result
}