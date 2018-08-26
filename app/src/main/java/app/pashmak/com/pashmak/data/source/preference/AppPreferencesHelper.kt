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

        private const val FIRST_NAME  = "FirstName"
        private const val LAST_NAME   = "LastName"
        private const val USER_AVATAR = "Avatar"
    }

    /**
     * provide saving and getting access token from preferences in order to use in with-token api services
     */
    var token: String by PreferenceDelegate(prefs, ACCESS_TOKEN, "")

    /**
     * provide saving and getting refresh token from preferences in order to use in [TokenAuthenticator]
     */
    var refreshToken: String by PreferenceDelegate(prefs, REFRESH_TOKEN, "")

    var firstName: String by PreferenceDelegate(prefs, FIRST_NAME, "")
    var lastName: String by PreferenceDelegate(prefs, LAST_NAME, "")
    var avatar: String by PreferenceDelegate(prefs, USER_AVATAR, "")

}
