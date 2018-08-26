package app.pashmak.com.pashmak.data.model.response

import app.pashmak.com.pashmak.data.model.response.error.ErrorModel

sealed class APIResponse<out T>
data class SuccessResponse<out T>(val value: T): APIResponse<T>()
data class ErrorResponse<out T>(val error: ErrorModel): APIResponse<T>()