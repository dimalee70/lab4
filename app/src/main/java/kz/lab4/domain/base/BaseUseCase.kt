package kz.lab4.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        val backgroundJob = scope.async {
            run(params)
        }
        scope.launch { onResult(backgroundJob.await()) }
    }

//    fun <T> onError(response: Response<T>): Either.Left<Failure.FeatureFailure> {
//        val gson = GsonBuilder().create()
//        val errorBody = gson.fromJson(response.errorBody()?.string(), BaseErrorDto::class.java)
//            ?: BaseErrorDto()
//
//        return Either.Left(Failure.FeatureFailure(featureError = errorBody))
//    }
}
