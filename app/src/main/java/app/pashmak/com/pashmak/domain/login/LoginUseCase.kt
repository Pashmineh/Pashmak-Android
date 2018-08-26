package app.pashmak.com.pashmak.domain.login

import app.pashmak.com.pashmak.data.model.login.LoginResponse
import app.pashmak.com.pashmak.data.repository.login.LoginRepository
import app.pashmak.com.pashmak.domain.BaseUseCase
import app.pashmak.com.pashmak.util.ErrorUtil
import io.reactivex.Flowable
import javax.inject.Inject

class LoginUseCase
@Inject constructor(
        errorUtil: ErrorUtil,
        private val loginRepository: LoginRepository)
    : BaseUseCase<LoginResponse>(errorUtil)
{
    private lateinit var userName: String
    private lateinit var password: String

    fun setParameters(userName: String, password: String) : LoginUseCase
    {
        return this.also {
            this.userName = userName
            this.password = password
        }
    }

    override fun buildUseCaseObservable(): Flowable<LoginResponse> {
        return loginRepository.login(userName, password)
    }
}