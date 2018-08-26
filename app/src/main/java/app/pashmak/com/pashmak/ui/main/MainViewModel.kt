package app.pashmak.com.pashmak.ui.main

import app.pashmak.com.pashmak.data.source.preference.AppPreferencesHelper
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel
    @Inject constructor(
            private val preferencesHelper: AppPreferencesHelper
    )
    : BaseViewModel()
{
    val fullName: String = "${preferencesHelper.firstName} ${preferencesHelper.lastName}"
    val avatar  : String = preferencesHelper.avatar
}