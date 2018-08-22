package app.pashmak.com.pashmak.util

import app.pashmak.com.pashmak.data.restful.APIs
import app.pashmak.com.pashmak.data.source.preference.AppPreferencesHelper
import dagger.Lazy
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * A Helper class that attempts to refresh api token if server returns UNAUTHORIZED error
 * @param apis An instance of [APIs] to call [APIs.refreshToken] api
 * @param appPref An instance of [AppPreferencesHelper] to save refreshed access token, token type,
 * and newly refresh token into preference.
 */
class TokenAuthenticator(val apis: Lazy<APIs>, val appPref: Lazy<AppPreferencesHelper>) : Authenticator {
    override fun authenticate(route: Route?, response: Response?): Request? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


//    override fun authenticate(route: Route?, response: Response?): Request? {
        //TODO implement refresh token
//        val refreshApi = apis.get().refreshToken(appPref.get().refreshToken)
//        val refreshResponse = refreshApi.execute()
//
//        return if (refreshResponse.isSuccessful) {
//            val body = refreshResponse.body()
//
//            /*appPref.get().token = body!!.accessToken
//            appPref.get().tokenType = body.tokenType
//            appPref.get().refreshToken = body.refreshToken*/
//
//            response?.request()?.newBuilder()?.header("Authorization", /*body.tokenType + */" " /*+ body.accessToken*/)?.build()
//        } else {
//            appPref.get().token = ""
//            appPref.get().tokenType = ""
//            appPref.get().refreshToken = ""
//
//            null
//        }
//    }
}