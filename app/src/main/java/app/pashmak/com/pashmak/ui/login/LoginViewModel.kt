package app.pashmak.com.pashmak.ui.login

import androidx.lifecycle.LifecycleOwner
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import app.pashmak.com.pashmak.util.MOBILE_REGEX
import app.pashmak.com.pashmak.util.livedata.NonNullLiveData
import app.pashmak.com.pashmak.util.toFlowable
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginViewModel
@Inject constructor()
    : BaseViewModel() {
    val phoneValue: NonNullLiveData<String> = NonNullLiveData("")
    val nationalCodeValue: NonNullLiveData<String> = NonNullLiveData("")
    val buttonIsEnable: NonNullLiveData<Boolean> = NonNullLiveData(false)

    fun observeInputChanges(lifecycleOwner: LifecycleOwner) {

        compositeDisposable.add(
                Flowable.combineLatest<String, String, Boolean>(
                        phoneValue.toFlowable(lifecycleOwner).throttleFirst(200, TimeUnit.MILLISECONDS),
                        nationalCodeValue.toFlowable(lifecycleOwner).throttleFirst(200, TimeUnit.MILLISECONDS),
                        BiFunction { phone, nationalCode ->
                            phoneIsValid(phone) && nationalCode.length == 10
                        })
                        .subscribeOn(Schedulers.io())
                        .subscribe {
                            buttonIsEnable.postValue(it)
                        }
        )


    }

    private fun phoneIsValid(phone: String): Boolean
    {
        return Regex(MOBILE_REGEX).matches(phone)
    }
}