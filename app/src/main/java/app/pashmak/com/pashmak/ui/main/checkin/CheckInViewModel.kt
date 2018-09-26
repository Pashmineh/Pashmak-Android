package app.pashmak.com.pashmak.ui.main.checkin

import app.pashmak.com.pashmak.domain.checkin.CheckInUseCase
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import javax.inject.Inject

class CheckInViewModel
    @Inject constructor(
            private val checkInUseCase: CheckInUseCase
    )
    : BaseViewModel()
{
}