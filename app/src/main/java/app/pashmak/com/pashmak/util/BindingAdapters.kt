package app.pashmak.com.pashmak.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("bindValue")
    fun bindEditTextValue(editText: EditText, field: MutableLiveData<String>) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                field.value = s.toString()
            }
        })

    }
}