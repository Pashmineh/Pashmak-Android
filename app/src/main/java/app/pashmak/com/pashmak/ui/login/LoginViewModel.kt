package app.pashmak.com.pashmak.ui.login

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import app.pashmak.com.pashmak.data.model.login.LoginResponse
import app.pashmak.com.pashmak.data.model.response.APIResponse
import app.pashmak.com.pashmak.data.model.response.ErrorResponse
import app.pashmak.com.pashmak.data.model.response.SuccessResponse
import app.pashmak.com.pashmak.domain.login.LoginUseCase
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import app.pashmak.com.pashmak.ui.main.MainActivity
import app.pashmak.com.pashmak.util.MOBILE_REGEX
import app.pashmak.com.pashmak.util.checkNationalCodeValidity
import app.pashmak.com.pashmak.util.livedata.NonNullLiveData
import app.pashmak.com.pashmak.util.toFlowable
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginViewModel
@Inject constructor(
        private val loginUseCase: LoginUseCase,
        private val navigator: LoginNavigator
) : BaseViewModel() {

    val phoneValue: NonNullLiveData<String> = NonNullLiveData("")
    val nationalCodeValue: NonNullLiveData<String> = NonNullLiveData("")
    val buttonIsEnable: NonNullLiveData<Boolean> = NonNullLiveData(false)
    val isLoading: NonNullLiveData<Boolean> = NonNullLiveData(false)

    fun observeInputChanges(lifecycleOwner: LifecycleOwner) {

        compositeDisposable.add(
                Flowable.combineLatest<String, String, Boolean>(
                        phoneValue.toFlowable(lifecycleOwner).throttleFirst(200, TimeUnit.MILLISECONDS),
                        nationalCodeValue.toFlowable(lifecycleOwner).throttleFirst(200, TimeUnit.MILLISECONDS),
                        BiFunction { phone, nationalCode ->
                            phoneIsValid(phone) && nationalCode.length == 10 && checkNationalCodeValidity(nationalCode)
                        })
                        .subscribeOn(Schedulers.io())
                        .subscribe {
                            buttonIsEnable.postValue(it)
                        }
        )


    }

    fun login() {
        isLoading.value = true
        loginUseCase.setParameters(phoneValue.value, nationalCodeValue.value).execute(compositeDisposable, ::onLoginResponse)
    }

    fun onLoginResponse(response: APIResponse<LoginResponse>) {
        isLoading.value = false
        when (response) {
            is SuccessResponse -> {
                activityAction{ navigator.startActivity(it, MainActivity::class.java, MainActivity.getCallingBundle()) }
            }
            is ErrorResponse -> {
                Log.d("Response State", "ErrorState")
            }
        }
    }

    private fun phoneIsValid(phone: String): Boolean {
        return Regex(MOBILE_REGEX).matches(phone)
    }
}