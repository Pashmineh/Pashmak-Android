package app.pashmak.com.pashmak.data.model.transaction

import app.pashmak.com.pashmak.R
import com.google.gson.annotations.SerializedName

enum class ReasonTypeEnum(val captionRes: Int)
{
    @SerializedName("TAKHIR") TAKHIR(R.string.debt_takhir),
    @SerializedName("SHIRINI") SHIRINI(R.string.debt_shirini),
    @SerializedName("JALASE") JALASE(R.string.debt_jalase)
}