package app.pashmak.com.pashmak.data.source.preference

import android.content.Context
import javax.inject.Inject

/**
 * With this helper we can access all shared preferences.
 * Every field uses [PreferenceDelegate] for managing get and set value
 */
class AppPreferencesHelper @Inject constructor(context: Context) {

    val prefs by lazy { context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) }

    companion object {
        private const val PREF_NAME = "pref_pashmak"
        private const val ACCESS_TOKEN = "UserToken"
        private const val REFRESH_TOKEN = "RefreshToken"
    }

    /**
     * provide saving and getting access token from preferences in order to use in with-token api services
     */
    var token: String by PreferenceDelegate(prefs, ACCESS_TOKEN, "")

    /**
     * provide saving and getting refresh token from preferences in order to use in [TokenAuthenticator]
     */
    var refreshToken: String by PreferenceDelegate(prefs, REFRESH_TOKEN, "")

}
