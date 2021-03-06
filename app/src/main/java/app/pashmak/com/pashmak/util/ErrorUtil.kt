package app.pashmak.com.pashmak.util

import app.pashmak.com.pashmak.data.model.response.error.ErrorModel
import app.pashmak.com.pashmak.data.model.response.error.ErrorStatus
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject


class ErrorUtil
@Inject constructor(val gson: Gson) {

    fun getErrorModel(t: Throwable?): ErrorModel {
        if (t == null) {
            return ErrorModel(ErrorStatus.NOT_DEFINED)
        }
        if (t is HttpException) {
            return if (t.code() == 401)
                ErrorModel(ErrorStatus.UNAUTHORIZED)
            else
                getHttpError(t.response().errorBody())
        }
        if (t is SocketTimeoutException) {
            return ErrorModel(ErrorStatus.TIMEOUT)
        }
        if (t is IOException) {
            return ErrorModel(ErrorStatus.NO_CONNECTION)
        }
        return if (t is NullPointerException) {
            ErrorModel(ErrorStatus.EMPTY_RESPONSE)
        } else ErrorModel(ErrorStatus.NOT_DEFINED)
    }

    private fun getHttpError(body: ResponseBody?): ErrorModel {
        return try {

            val listType = object : TypeToken<List<ErrorModel>>() {}.type
            val errorModelList: List<ErrorModel> = gson.fromJson(body!!.string(), listType)
            errorModelList[0].also {
                it.errorStatus = ErrorStatus.BAD_RESPONSE
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            ErrorModel(ErrorStatus.NOT_DEFINED)
        }

    }

}