package app.pashmak.com.pashmak.domain

import app.pashmak.com.pashmak.data.model.response.APIResponse
import app.pashmak.com.pashmak.data.model.response.ErrorResponse
import app.pashmak.com.pashmak.data.model.response.SuccessResponse
import app.pashmak.com.pashmak.data.model.response.error.ErrorStatus
import app.pashmak.com.pashmak.util.ErrorUtil
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase<T>(private val errorUtil: ErrorUtil) {

    abstract fun buildUseCaseObservable(): Flowable<T>

    fun execute(
            compositeDisposable: CompositeDisposable,
            onResponse: (APIResponse<T>) -> Unit
            /*, onTokenExpire: (() -> Unit)? = null*/
    ): Disposable {
        return this.buildUseCaseObservable()
                .onBackpressureLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onResponse(SuccessResponse(it)) },
                        {
                            val error = errorUtil.getErrorModel(it)

/*                            if (error.errorStatus == ErrorStatus.UNAUTHORIZED)
                                onTokenExpire?.invoke()*/

                            onResponse(ErrorResponse(error))
                        }).also { compositeDisposable.add(it) }
    }

    fun <Request> executeAndKeepRequest(
            compositeDisposable: CompositeDisposable,
            request: Request,
            onResponse: (response: APIResponse<T>, request: Request) -> Unit
            /*, onTokenExpire: () -> Unit*/
    ): Disposable {
        return this.buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onResponse(SuccessResponse(it), request) },
                        {
                            val error = errorUtil.getErrorModel(it)

/*                            if (error.errorStatus == ErrorStatus.UNAUTHORIZED)
                                onTokenExpire()
                            else {*/
                                onResponse(ErrorResponse(error), request)
//                            }
                        })
                .also { compositeDisposable.add(it) }
    }
}
